plugins {
	kotlin("jvm")
	id("com.withertech.architectury.kotlin.plugin")
}

task("verify") {
	assert(
		kotlin.compilerOptions.freeCompilerArgs.get().containsAll(
			buildList {
				add("-Xmulti-platform")
				add("-Xexpect-actual-linker")
				add("-Xexpect-actual-classes")
			}
		)
	)
}