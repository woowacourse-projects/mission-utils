plugins {
    `java-library`
}

group = "camp.nextstep.edu"
version = "1.1.0"

repositories {
    mavenCentral()
}

dependencies {
    api("org.assertj:assertj-core:3.21.0")
    api("org.junit.jupiter:junit-jupiter:5.8.1")
    api("org.mockito:mockito-inline:3.12.4")
}

tasks.test {
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
