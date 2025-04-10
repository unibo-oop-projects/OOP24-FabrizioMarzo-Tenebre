plugins{
    java
    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("ArmataTenebre") 
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.12.0") // Dependencies for Apache library
}
