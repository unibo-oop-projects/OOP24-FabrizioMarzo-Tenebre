plugins{
    java
    application
    
    id("org.danilopianini.gradle-java-qa") version "1.108.0"
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("ArmataTenebre") 
}

dependencies {
    // Suppressions for SpotBugs
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")

    implementation("org.apache.commons:commons-lang3:3.12.0") // Dependencies for Apache library
}
