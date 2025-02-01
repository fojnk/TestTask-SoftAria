plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("org.jetbrains:annotations:24.0.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.eclipse.angus:angus-mail:2.0.1")
    implementation("com.beust:jcommander:1.69")
    implementation("org.apache.logging.log4j:log4j-core:2.16.0")
    implementation("org.apache.logging.log4j:log4j-api:2.16.0")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.example.Main"
    }
}

tasks.test {
    useJUnitPlatform()
}
