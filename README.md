After setup the IMDB and installing the solution, following request url can be invoked throgh postmat or browser . All request URL is GET Request.

Requirement #1 (easy):
IMDb copycat: Present the user with endpoint for allowing them to search by movie’s primary title or original title. 
The outcome should be related information to that title, including cast and crew.

Request URL for Requirement 1:
GET http://localhost:8087/luna/title?titleName=Carmencita
Response for Requirement 1:
{
    "dataObject": {
        "titleType": "short",
        "primarytitle": "Carmencita",
        "originaltitle": "Carmencita",
        "isadult": false,
        "startyear": 1894,
        "endyear": null,
        "runtimeminutes": 1,
        "genres": "Documentary,Short",
        "primaryCastCrewDetails": [
            {
                "primaryname": "Carmencita",
                "category": "self",
                "job": null,
                "characters": "['Self']"
            },
            {
                "primaryname": "William K.L. Dickson",
                "category": "director",
                "job": null,
                "characters": null
            },
            {
                "primaryname": "William Heise",
                "category": "cinematographer",
                "job": "director of photography",
                "characters": null
            },
            {
                "primaryname": "Oskar Messter",
                "category": "producer",
                "job": "producer",
                "characters": null
            }
        ]
    },
    "httpStatus": "OK",
    "success": true
}

Requirement #2 (easy):
Top rated movies: Given a query by the user, you must provide what are the top rated movies for a genre 
(If the user searches horror, then it should show a list of top rated horror movies).

Request URL for Requirement 2:
GET http://localhost:8087/luna/generes?generesName=horror
Response for Requirement 2:
{
    "dataObject": [
        {
            "primaryTitle": "The NoSleep Podcast",
            "originalTitle": "The NoSleep Podcast",
            "averagerating": "9.8"
        },
        {
            "primaryTitle": "I'm Your Biggest Fan",
            "originalTitle": "I'm Your Biggest Fan",
            "averagerating": "9.8"
        },
        {
            "primaryTitle": "Rattlers 2",
            "originalTitle": "Rattlers 2",
            "averagerating": "9.8"
        }
    ],
    "httpStatus": "OK",
    "success": true
}



Requirement #3 (difficult):
Six degrees of Kevin Bacon: Given a query by the user, you must provide what’s the degree of separation between the person (e.g. actor or actress) the user has entered and Kevin Bacon.

Request URL for Requirement 3:
GET http://localhost:8087/luna/dos?name=Emma Watson
Response for Requirement 3:
{
    "dataObject": "Degree of separation for given name Emma Watson and default name Kevin Bacon is 2",
    "httpStatus": "OK",
    "success": true
}
