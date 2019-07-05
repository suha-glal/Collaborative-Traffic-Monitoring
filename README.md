# Using Mobile Phone GPS for Real-time, Collaborative Traffic Monitoring 

Soha Glal and Ashraf Khalil

Abu Dhabi University

suha.glal@gmail.com, ashraf.khalil@adu.ac.ae

<b>1.	Introduction</b>


With the ever increasing number of cars on city roads, traffic congestion has become a significant and constant problem in urban areas around the world. Traffic reports have become an integral part of news reports. Many cities around the world are dedicating TV and radio channels to report the traffic conditions continuously. These channels often depend on conventional methods to collect information about traffic conditions such as mounted video cameras, inductive loop detection, license plate readers, police and witness reports, and traffic airplanes. These methods are very costly to use and maintain and are not scalable, thus they are usually limited to heavily-used stretches of highways. Moreover, even live radio reports tend to focus only on specific locations. Drivers cannot go online to check this information on demand and get customized directions that avoid congested streets. 
These days most high-end GPS devices and online maps, such as Google and Yahoo! maps, provide the user with some kind of traffic data and enable the user to plan routes with the least amount of traffic. However, such traffic data is based on historical reports rather than real-time information and thus is useless in the case of sudden or recent changes in road conditions such as accidents, road repair, or newly constructed roads.

<b>2.	System Description </b>


To address most of the above mentioned limitations, we propose an approach that allows GPS-equipped mobile phones to automatically share their location and speed data while driving on the roads. The data is collected by a central service from all participating vehicles and is continuously processed in order to infer the traffic flow. The output will be displayed on Google maps with color-coding on streets reflecting the traffic conditions and speed of flow on individual streets. The displayed maps convey real-time traffic information on demand in a usable way to drivers. 
This novel approach is collaborative, which means it depends on collective participation from a representative sample of road vehicles. This collective participation is very realistic given the advances in smart phone technologies and the fact that many of the mobile phones that are currently sold are equipped with built-in GPS devices in addition to unlimited Internet connection packages. Additionally, users have a great incentive to participate in such a service as they will be able to obtain real-time traffic information, and the more they participate the more accurate this information will be. 
 	


2.1 System Architecture 


The system is built using client/server architecture. The client application, running on GPS-enabled cell phones, sends location and speed data for the vehicle (obtained from the GPS readings) to a centralized server. This mobile client is written using Java because it is a platform-independent programming language. The server application collects the data asynchronously from all the clients,processes it, and then displays it on Google maps using superimposed color-coding to indicate the traffic conditions of the city.The map with traffic information can be downloaded by the client application and it can also be accessed on the web by anyone as a Mashup application. 

2.2 Tools 


The client application is written using J2ME language, Java Location API (JSR179), and Java Network API. The tool we used to develop the client application is NetBeans IDE. The server application is developed using C# and the Net platform. The website which displays the traffic map is written using PHP language, MySql database and Google map API. The website is developed using the WampServer application.

2.3 Scenario of Use 


Ahmed has an important meeting in his job today and should not be late under any circumstances. He has already downloaded our traffic monitoring client on his N95 cell phone. Just before he gets in the car he opens his cell phone and in one click he downloads the map of the city which displays color-coding for the traffic conditions on the streets. He realizes that his usual route, Sheikh Zayed Road, is more congested than usual but that Emirates Road has normal traffic flow. He decides to take Emirates Road to get to work even though it is double the travel distance of his usual route. Ahmed arrives to work on time, but his friend Rashed, who does not use the traffic monitoring system, arrives just before the end of the meeting and is very embarrassed. After the meeting Rashed asks Ahmed if he was also delayed by the accident on Sheikh Zayed road. He is surprised to hear about the service Ahmed is using and immediately asks him for the link to download this service.

<b>3.	Limitation </b>


The reliability and accuracy of the collaborative traffic monitoring service inherently depends on contributions from users. The greater the number of participants, the more reliable the service will be. However, we expect this limitation will be overcome by the great value offered by the service and the incentives users gain through participation, specifically the ability to download maps marked with traffic info on demand. To initially bootstrap the service, we plan to approach taxi service providers in order to ask their drivers to use the service. By soliciting the service of taxi companies we ensure a large set of high-quality data due to the fact that taxi vehicles roam the streets continuously. Another major concern of this service is the privacy concern of sharing location and speed information. Users rightly might fear the service would be used to track their location and movement habits. However, client application will not send any indentifying information about the user so that all participants will be anonymous to the server. Furthermore, we are planning to implement state-of-the-art privacy-preserving methodologies that are developed for location-aware applications [1]. 


<b>4.	 Conclusion</b>


We have presented a novel approach to address the pertinent issue of traffic monitoring. Our system provides real-time traffic information to mobile clients and web users which will help to alleviate traffic congestion in crowded cities and reduces the chance of road accidents. Such a system is an ideal solution for the UAE given the abundance of high-end cell phones that usually come equipped with GPS and the availability of fast and reliable Internet connectivity. Additionally, UAE cities such as Dubai and Abu Dhabi are witnessing a tremendous growth in economy and population which puts a huge strain on roads and makes these cities major beneficiaries of such a solution. Reducing traffic jams directly impacts the productivity and quality of life of the residents of the country.

<b>References</b>


[1] B. Hoh and M. Grusteser. Protecting location privacy through path confusion. In proceedings of IEEE/Create-Net Secure Comm, Athens, Greece, September 2005.
