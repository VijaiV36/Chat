 
*************************************  Name- Vijai Veeraragavan  ************************************************

*************************************  Net ID- vxv0965           ************************************************

*************************************  UTA ID- 1001670965        ************************************************

Instruction to Run the code:

1) Code was done in NetBeans IDE, with JDK 1.8.0.

2) Run the Server.java form.(using Shift-F6)

5) Check the TextArea in the Server Window,minimize the empty window.

6) Run the Client.java form.(using Shift-F6) 

7) A frame containing the Client operations is opened, enter the username on the TextField and select the "Add Client", to register the client on the server side. 

8) Run the Client.java form for another 2 times.(using the same steps as above and register the Client's username).

9) Check the Server's TextArea to see whether the clients have successfully registered or not. The Client's socket details are displayed.

10) Enter the message to be sent on the corresponding TextField, the send message button will show a prompt where the client can select 1 option out of the 2 :

		1. Deliver the message to a single, designated client (1-to-1); or, - the designated client's username must be specified. 
		2. Deliver the message to all clients (1-to-N). 

All the messages which are to be sent, are shown on the server's output window in HTTP Message Format and checked whether the status is Ok or not
before being facilitated to the recipients. 

11) Select Clear to reset the TextField. 

12) If the client wants to logout , then selecting logout will kill the process and disconnect the client from the server. 

13) Finally close the server form using the STOP Server button present in the Server Window. 

References :-

1.https://www.geeksforgeeks.org/multi-threaded-chat-application-set-1/

2.https://www.geeksforgeeks.org/multi-threaded-chat-application-set-2/