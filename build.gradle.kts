import org.beryx.jlink.JPackageTask

plugins {
    java
    application
    id("com.diffplug.spotless") version "6.25.0"
    id("org.beryx.jlink") version "3.1.1"
    pmd
    jacoco
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

val markdownDoclet by configurations.creating

dependencies {
    markdownDoclet("org.jdrupes.mdoclet:doclet:4.1.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.14.2")
//    implementation("org.jline:jline:3.27.1")
}

tasks.withType(Test::class) {
    useJUnitPlatform()
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
configurations.forEach {
    println("## Configuration ${it.name}")
    it.dependencies.forEach {
        println("  - ${it.name}")
    }
}





pmd {
    toolVersion = "7.9.0"
    isConsoleOutput = false
    isIgnoreFailures = true
    ruleSetFiles(files("config/pmd.xml"))
}

tasks.javadoc {
    options.docletpath = markdownDoclet.files.toList()
    options.doclet = "org.jdrupes.mdoclet.MDoclet"
    options.quiet()
    (options as CoreJavadocOptions).addStringOption("Xdoclint:-html")

    options.jFlags = listOf(
        "--add-exports=jdk.compiler/com.sun.tools.doclint=ALL-UNNAMED",
        "--add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED",
        "--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
        "--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED",
        "--add-exports=jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED",
        "--add-exports=jdk.javadoc/jdk.javadoc.internal.tool=ALL-UNNAMED",
        "--add-exports=jdk.javadoc/jdk.javadoc.internal.doclets.toolkit=ALL-UNNAMED",
        "--add-opens=jdk.javadoc/jdk.javadoc.internal.doclets.toolkit.resources.releases=ALL-UNNAMED")
}

tasks.test {
    finalizedBy(tasks.named("jacocoTestReport")) // report is always generated after tests run
}
tasks.named<Task>("jacocoTestReport") {
    dependsOn(tasks.test) // tests are required to run before generating the report
}
