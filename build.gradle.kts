plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.register<Jar>("runtimeJar") {
    archiveClassifier.set("runtime")

    // Use only the classes from the package org.example.runtime
    from("build/classes/java/main") {
        include("org/example/luja/runtime/**")
    }

    // Ensure classes are compiled before creating the jar
    dependsOn(tasks.named("classes"))
}

tasks.named("build") {
    dependsOn("runtimeJar")
}
repositories {
    mavenCentral()
}


dependencies {
    implementation("org.antlr:antlr4:4.13.1")
    implementation("org.ow2.asm:asm:9.5")
    implementation("org.ow2.asm:asm-util:9.5")

    implementation("org.apache.commons:commons-lang3:3.14.0")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.5.0")

    implementation("org.springframework.boot:spring-boot-starter")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}