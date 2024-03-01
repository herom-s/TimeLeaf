@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
}

android {
        namespace = "com.project.timeleaf.feature.onboarding"
        compileSdk = 34

        buildFeatures {
            compose = true
        }

        with (defaultConfig) {
            minSdk = 28
            targetSdk = 34
        }

        compileOptions {
            isCoreLibraryDesugaringEnabled = true

            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
}

dependencies {
    implementation(project(mapOf("path" to ":core:data")))
    implementation(project(mapOf("path" to ":core:model")))
    implementation(project(mapOf("path" to ":core:designsystem")))

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.hilt)
    implementation(libs.kotlin.coroutines)
    implementation(libs.lifecycle.runtime.compose)
    implementation(libs.navigation)
    implementation(libs.navigation.hilt)
    implementation(libs.androidx.ui.tooling.preview.android)

    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    coreLibraryDesugaring(libs.desugar)
}