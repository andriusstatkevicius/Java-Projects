This was a homework assignment from one company where I needed to collect data from the website http://www.norwegian.com

I needed to collect departure airport, arrival airport, connection airport, departure time, arrival time, cheapest price and taxes for all flights from OSL (Oslo) to RIX (Riga)
departing from 2018-05-01 to 2018-05-31 (31 departure days). Data should only have been collected for direct flights.

I noticed that the URL link changes by the selected departure day as the departure and arrival airport remain the same. Theregore could use the exact URL link with all the parameters for
all the direct flights from OSL to RIX and only change the date of departure. Moreover, there are no flights on Saturdays in May from OSL to RIX, so one can exclude Saturdays from search as well. 
This would reduce the number of requests to website to the amount of 1335. Therefore I was changing the URL dynamically by changing the departure day in it and connecting to it in order to extract the relevant data. 
I used a JSOUP library to convert HTML into a document. Afterwards, I extracted necessary elements from a document and made lists out of those elements (for such cases if there are more than one flight per selected departure day).
Out of the created lists I generated flight objects with parameters of all the relevant data and added them to the list so I would be able to print them out and find the cheapest flight. The class that should be run is “App”.