https://yggdrasilgaming.com/games/vikings-go-wild/

This is one of our games made available in Play-For-Free system. Our games are using a couple request to server to
authenticate and get translations in many languages etc. The task consists investigating communication between
frontend client and backend server and recreating it in any JVM based test framework.
Create automated test that:

• Logs in into system as anonymous player (using fn=authenticate request)
• When sessid parameter is received, use it to send spin command (fn=play) until you win any
• money (end condition: in json respone - field data.wager.bets[0].wonamount > 0)
Nice to haves:

• Use build tool (either Maven, sbt or Gradle)
• Create configuration file which will store common variable parameters ex. host name, organization

Good luck and have fun! 