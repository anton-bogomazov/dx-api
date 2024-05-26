plugins {
    id(Conventions.spring_module)
}

dependencies {
    implementation(project(":user-api:persistence"))

    implementation(ObservabilityLibs.micrometer)
    implementation(PersistenceLibs.spring_data_jdbc)
}
