node {
	def app

	System.setProperty("org.jenkinsci.plugins.durabletask.BourneShellScript.HEARTBEAT_CHECK_INTERVAL", "86400")

	stage('Clone repository') {
		echo 'Cloning repository...'
		checkout scm
		echo 'Repository cloned'
	}

	stage('Build api image') {
		echo 'Building api image...'
		dir('api') {
			retry(3) {
				app = docker.build("horse_market_api_image:latest")
			}
		}
		echo 'Image built'
	}


	/* stage('Compile and build front') {
		dir('web') {
			stage('Compile front') {
				sh 'mvn clean package'
			}

			stage('Build front image') {
				echo 'Building image...'
				retry(3) {
					app = docker.build("horse_market_api_image:latest")
				}
				echo 'Image built'
			}
		}
	} */

	stage('Deploying horse_market') {
		sh '/home/hera/scripts/restart_composition.sh'
	}
}
