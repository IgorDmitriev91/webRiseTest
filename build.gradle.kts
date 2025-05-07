plugins {
    java
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.0.1"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}
ext {
    set("springdocVersion", "2.8.6")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.liquibase:liquibase-core")
    implementation("org.testcontainers:postgresql")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${property("springdocVersion")}")
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.mapstruct:mapstruct:1.6.3")

    compileOnly("org.projectlombok:lombok")
    //developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

sourceSets {
    getByName("main") {
        java {
            srcDir("$buildDir/generated/src/main/java")
        }
    }
}

tasks.named<JavaCompile>("compileJava") {
    dependsOn(tasks.named("openApiGenerate"))
}

openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$rootDir/src/main/resources/static/web-rise-test-v0.0.1.yaml")
    outputDir.set(layout.buildDirectory.dir("generated").map { it.asFile.absolutePath })
    apiPackage.set("com.example.webrisetest.controller")
    invokerPackage.set("com.example.webrisetest.invoker")
    modelPackage.set("com.example.webrisetest.dto")
    generateApiTests.set(false)

    configOptions.set(
            mapOf(
                    "dateLibrary" to "java8",
                    "useSpringBoot3" to "true",
                    "useTags" to "true",
                    "interfaceOnly" to "true",
                    "skipDefaultInterface" to "true"
            )


    )
}