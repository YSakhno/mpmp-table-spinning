/*
 * Solver for Matt Parker's Maths Puzzle (MPMP): Can you spin the table?
 *
 * See this YouTube video for more info about the puzzle itself: https://youtu.be/T29dydI97zY
 *
 * Written in 2020 by Yuri Sakhno.
 */
plugins {
    kotlin("jvm") version "1.3.71"
    application
}

repositories {
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.3.71"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
}

application {
    mainClassName = "io.ysakhno.mpmp.tablespin.AppKt"
}
