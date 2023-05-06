plugins {
	id("com.android.application")
	kotlin("android")
}

android {
	compileSdk = 33
	namespace = "com.careem.pizzaorderingapp"
	viewBinding.isEnabled = true

	defaultConfig {
		applicationId = "com.careem.pizzaorderingapp"
		minSdk = 21
		targetSdk = 33
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

dependencies {
	implementation("androidx.core:core-ktx:1.10.0")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.9.0")
//    implementation "io.reactivex.rxjava3:rxjava:3.1.3"
//    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0"
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.5")
}