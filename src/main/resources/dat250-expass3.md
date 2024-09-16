# Report DAT250
From last time we managed to get up React components, which we will use to register users and polls and vote for different polls. We have also added css files, which makes the layout nicer. 


## Challenges
* It was hard to make the frontend communicate with the backend. I still struggle with this, because we get an error when we press the submit buttons. The error is that the component doesn't find the backend server. I also tried a bit without actually having the SpringBoot server up and running. Turning it on was coming one step closer tot the solution.
* It has also been hard to implement the tests from expass2.md. We worked a bit more on this, however, the tests that are there now are not working. We will look more into this.

## To do
* Work more on the backend and front end communication. It seems like there is a problem about which ports are being listened to and communicated with. 
* Actually have the components work as intended. We have set up the different components, however, the logic is not yet working. From last time we now see the html, and it is possible to input text to register users, add poll options and write input for the poll options. We also have a component about voting for already created polls, however, the options are not being displayed yet.