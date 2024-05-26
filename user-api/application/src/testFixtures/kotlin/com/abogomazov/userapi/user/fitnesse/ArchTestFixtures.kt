package com.abogomazov.userapi.user.fitnesse

import com.tngtech.archunit.core.importer.ImportOption

val DoNotImportMetrics = ImportOption { !it.contains("com/abogomazov/userapi/user/metrics") }
