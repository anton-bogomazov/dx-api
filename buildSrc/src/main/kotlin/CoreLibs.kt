object CoreLibs {
    private object Versions {
        const val spring_boot = Global.spring_boot
        const val arrow = "1.2.4"
        const val koin = "3.5.0"
    }

    const val arrow = "io.arrow-kt:arrow-core:${Versions.arrow}"
    const val koin = "io.insert-koin:koin-core:${Versions.koin}"
    const val spring_dependencies = "org.springframework.boot:spring-boot-dependencies:${Versions.spring_boot}"
}

