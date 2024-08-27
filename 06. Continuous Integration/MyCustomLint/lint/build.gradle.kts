plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

//java {
//    sourceCompatibility = JavaVersion.VERSION_1_8
//    targetCompatibility = JavaVersion.VERSION_1_8
//}
val lintVersion = "31.5.0"

dependencies {
    compileOnly("com.android.tools.lint:lint-api:$lintVersion")
    compileOnly("com.android.tools.lint:lint-checks:$lintVersion")
    testImplementation("com.android.tools.lint:lint-tests:$lintVersion")
}

tasks.jar {
    manifest {
        attributes("Lint-Registry-v2" to "com.avrians.mycustomlint.lint.MyIssueRegistry")
    }
}