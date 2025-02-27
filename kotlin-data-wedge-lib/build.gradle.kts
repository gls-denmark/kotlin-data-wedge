plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("kotlinx-serialization")
}

val libraryGroupId = "dk.gls"
val libraryArtifactId = "kotlin-data-wedge"
val libraryVersion = "0.0.10"

android {
    namespace = "dk.gls.kdw"
    compileSdk = 34

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

kotlin {
    jvmToolchain(17)
}



dependencies {

    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.21")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    /* Serialization */
    val kotlinxSerializationJsonVersion = "1.4.0"
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationJsonVersion")

    /* Test */
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.robolectric:robolectric:4.10.3")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/gls-denmark/kotlin-data-wedge")
            credentials {
                username = getEnvironmentVariable("GIT_USERNAME")
                password = getEnvironmentVariable("GIT_AUTH_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("release") {
            groupId = libraryGroupId
            artifactId = libraryArtifactId
            version = libraryVersion

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

fun getEnvironmentVariable(variableName: String): String {
    return try {
        //Try to get the variable from gradle.properties
        extra[variableName].toString()
    } catch (e: ExtraPropertiesExtension.UnknownPropertyException) {
        //Try to get the variable from the environment
        System.getenv(variableName)
            .let {
                if (it?.isNotEmpty() == true) {
                    "$it"
                } else {
                    "\"\""
                }
            }
    }
}