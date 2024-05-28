package com.withertech.architectury.kotlin.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

abstract class ArchitecturyKotlinPlugin : Plugin<Project> {
	override fun apply(project: Project) {
		project.extensions.getByType(KotlinJvmProjectExtension::class.java).apply {
			compilerOptions {
				val args = freeCompilerArgs.get().toMutableList()
				args.addAll(
					buildList {
						add("-Xmulti-platform")
						add("-Xexpect-actual-linker")
						add("-Xexpect-actual-classes")
					}
				)
				freeCompilerArgs.set(args)
			}
		}
	}
}
