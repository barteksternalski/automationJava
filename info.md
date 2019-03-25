##pull new images:
docker pull \<imageName\>
###selenium hub images to pull:	
	selenium/hub
	selenium/node-firefox         
	selenium/node-chrome 
	selenium/node-firefox-debug   
	selenium/node-chrome-debug
###zalenium images to pull    
    docker pull elgalu/selenium
	docker pull dosel/zalenium
##check installed images:
	docker images
##start selenium grid
###selenium hub
	docker run -d -p 4444:4444 --name selenium-hub selenium/hub
###zalenium
	docker run --rm -ti --name zalenium -p 4444:4444 -p 5555:5555 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos dosel/zalenium start
##start browser on selenium hub grid:
	docker run -d --link selenium-hub:hub selenium/node-chrome
	docker run -d --link selenium-hub:hub selenium/node-firefox
##check containers:
	docker ps -a
##terminate:
	docker kill $(docker ps -q)
	docker rm $(docker ps -a -q)
	docker rmi $(docker images -q)
###batch
	FOR /f "tokens=*" %i IN ('docker ps -q') DO docker stop %i

	
	