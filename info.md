##zalenium images to pull    
    docker pull elgalu/selenium
	docker pull dosel/zalenium
##check installed images:
	docker images
##start selenium grid
	docker run --rm -ti --name zalenium -p 4444:4444 -p 5555:5555 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos dosel/zalenium start
#####live view
    http://localhost:4444/grid/admin/live
##check containers:
	docker ps -a
##terminate:
	docker kill $(docker ps -q)
	docker rm $(docker ps -a -q)
	docker rmi $(docker images -q)
###batch
	FOR /f "tokens=*" %i IN ('docker ps -q') DO docker stop %i

	
	