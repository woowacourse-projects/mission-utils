plugins {
    `java-library`
    `maven-publish`
}

group = "camp.nextstep.edu"
version = "1.2.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

val mockito = "org.mockito:mockito-core:5.14.1"
val mockitoAgent = configurations.create("mockitoAgent")

dependencies {
    api("org.assertj:assertj-core:3.26.3")
    api("org.junit.jupiter:junit-jupiter:5.11.0")
    implementation(mockito)
    mockitoAgent(mockito) { isTransitive = false }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group as String
            artifactId = project.name
            version = version as String

            from(components["java"])
        }
    }
}

tasks.test {
    jvmArgs("-javaagent:${mockitoAgent.asPath}")
    useJUnitPlatform()
    testLogging {
        afterSuite(
            KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
                if (desc.parent == null) {
                    val output =
                        """
                        |  Results: ${result.resultType} (${result.testCount} tests, ${result.successfulTestCount} passed, ${result.failedTestCount} failed, ${result.skippedTestCount} skipped)  |
                        """.trimIndent()
                    val line = "-".repeat(output.length)
                    println("$line\n$output\n$line")
                }
            }),
        )
    }
}
