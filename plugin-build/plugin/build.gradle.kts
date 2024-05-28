import org.gradle.api.internal.artifacts.DefaultModuleIdentifier
import org.gradle.api.internal.artifacts.dependencies.DefaultMinimalDependency
import org.gradle.api.internal.artifacts.dependencies.DefaultMutableVersionConstraint
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
	`java-gradle-plugin`
	alias(libs.plugins.pluginPublish)
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(gradleApi())
	implementation(libs.plugins.kotlin.toLibrary())

	testImplementation(libs.junit)
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_1_8.toString()
	}
}

gradlePlugin {
	plugins {
		@Suppress("UnstableApiUsage")
		create(property("ID").toString()) {
			id = property("ID").toString()
			implementationClass = property("IMPLEMENTATION_CLASS").toString()
			version = property("VERSION").toString()
			description = property("DESCRIPTION").toString()
			displayName = property("DISPLAY_NAME").toString()
			tags = listOf("architectury", "kotlin")
			website = property("WEBSITE").toString()
			vcsUrl = property("VCS_URL").toString()
		}
	}
}

tasks.create("setupPluginUploadFromEnvironment") {
	doLast {
		val key = System.getenv("GRADLE_PUBLISH_KEY")
		val secret = System.getenv("GRADLE_PUBLISH_SECRET")

		if (key == null || secret == null) {
			throw GradleException("gradlePublishKey and/or gradlePublishSecret are not defined environment variables")
		}

		System.setProperty("gradle.publish.key", key)
		System.setProperty("gradle.publish.secret", secret)
	}
}

fun ProviderConvertible<PluginDependency>.toLibrary() = asProvider().toLibrary()

fun Provider<PluginDependency>.toLibrary() = get().toLibrary()

fun PluginDependency.toLibrary() =
	DefaultMinimalDependency(
		DefaultModuleIdentifier.newId(pluginId, "$pluginId.gradle.plugin"),
		DefaultMutableVersionConstraint(version)
	)
