package com.withertech.architectury.kotlin.plugin

import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.junit.Test

class ArchitecturyKotlinPluginPluginTest {
	@Test
	fun `compiler args are added to the project`() {
		val project = ProjectBuilder.builder().build()
		project.pluginManager.apply(KotlinPluginWrapper::class.java)
		project.pluginManager.apply("com.withertech.architectury.kotlin.plugin")

		assert(
			project.extensions.getByType(KotlinJvmProjectExtension::class.java).compilerOptions.freeCompilerArgs.get()
				.containsAll(
					buildList {
						add("-Xmulti-platform")
						add("-Xexpect-actual-linker")
						add("-Xexpect-actual-classes")
					}
				)
		)
	}
}
