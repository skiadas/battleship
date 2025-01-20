plugins {
    java
    application
    jacoco
    id("com.diffplug.spotless") version "6.25.0"
    id("org.beryx.jlink") version "3.1.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(22) // Replace with your desired version
        vendor = JvmVendorSpec.AMAZON
    }
    withJavadocJar()
    withSourcesJar()
}

jlink {
    imageName = "${project.name}-${version}"
    imageZip = layout.buildDirectory.file("distributions/${imageName}.zip")
    options.addAll(listOf("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages"))
    launcher {
        name = "battleship"
    }
}

tasks.assemble {
    dependsOn(tasks.named("jlinkZip"))
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.14.2")
//    implementation("org.jline:jline:3.27.1")
}

tasks.withType(Test::class) {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}

application {
    mainClass = "main.Main"
    applicationDefaultJvmArgs += "--enable-native-access=ALL-UNNAMED"
}

tasks.named<JavaExec>("run") {
    mainClass = "main.Main"
    standardInput = System.`in`
    jvmArgs("--enable-native-access=ALL-UNNAMED")
}

spotless {
    java {
        importOrder()
        removeUnusedImports()
        cleanthat()
        googleJavaFormat().aosp().reflowLongStrings()
    }
}
