plugins {
	id("arkwright-root")
	id("arkwright-minecraft")
	id("maven-publish")
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			artifactId = project.base.archivesName.get()
			from(components["java"])
		}
	}
}
