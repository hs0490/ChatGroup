## ChatGroup

It is a chat application with several concurrent users using UDP Socket.


It performs following operations:
  - Clients advertise themselves to server.
  - Server notifies all clients as to who's online.
  - Instantiate n, n>2 clients. For each client, server starts a thread.
  - Say clients 1, 2 and 3 are online.
  - Client 1, can talk to client 2, or to both client 2 and 3. Etc.
  
  
### SAMPLE OUTPUT

- SERVER:

Waiting for Clients:

HIMANSHU is Online
BRYAN is Online
HARSH is Online
- BRYAN is Offline

-------------------------------------------------------------------------------------------------------------------------

-CLIENT 1:         
                                                                  
-Please Enter Following Commands:
-To Start chat : <START>-NAME
-To Connect Client : <CONNECT>-NAME
-To List Online Clients : <LIST>
-Type Message On Console To Send Message
-To Exit Chat : <EXIT>
-To offline : <OFFLINE>
=================TYPE MESSAGE BELOW=======================

-<START>-HIMANSHU
-<CONNECT>-BRYAN
-HELLO BRYAN. HOW ARE YOU?
-BRYAN: I AM DOING PRETTY GOOD.
-LETS ADD HARSH INTO GROUP.
-BRYAN: OK.
-<CONNECT>-HARSH
-HELLO HARSH
-HARSH: HELLO HIMANSHU. WHATS UP.
-BRYAN: WELCOME HARSH
-HARSH: THANKS BRYAN. HOW ARE YOY BRO?
-BRYAN: SORRY GUYS, I HAVE TO GO. TALK TO U LATER.
-BYE BRYAN
-HARSH: BYE BRYAN
-BRYAN has exit the chat

-------------------------------------------------------------------------------------------------------------------------------------
-CLIENT 2:

-Please Enter Following Commands:
-To Start chat : <START>-NAME
-To Connect Client : <CONNECT>-NAME
-To List Online Clients : <LIST>
-Type Message On Console To Send Message
-To Exit Chat : <EXIT>
-To offline : <OFFLINE>
=================TYPE MESSAGE BELOW=======================

-<START>-HIMANSHU
-<CONNECT>-BRYAN
-HELLO BRYAN. HOW ARE YOU?
-BRYAN: I AM DOING PRETTY GOOD.
-LETS ADD HARSH INTO GROUP.
-BRYAN: OK.
-<CONNECT>-HARSH
-HELLO HARSH
-HARSH: HELLO HIMANSHU. WHATS UP.
-BRYAN: WELCOME HARSH
-HARSH: THANKS BRYAN. HOW ARE YOY BRO?
-SORRY GUYS, I HAVE TO GO. TALK TO U LATER.
-HIMANSHU: 
-HIMANSHU: BYE BRYAN
-HARSH: BYE BRYAN
-<EXIT>
-<OFFLINE>
-Client is Offline

-------------------------------------------------------------------------------------------------------------------------------------
-CLIENT 3:

-Please Enter Following Commands:
-To Start chat : <START>-NAME
-To Connect Client : <CONNECT>-NAME
-To List Online Clients : <LIST>
-Type Message On Console To Send Message
-To Exit Chat : <EXIT>
-To offline : <OFFLINE>
=================TYPE MESSAGE BELOW=======================

-<START>-HARSH
-HIMANSHU: HELLO HARSH
-<CONNECT>-HIMANSHU
-HELLO HIMANSHU. WHATS UP.
-BRYAN: WELCOME HARSH
-<CONNECT>-BRYAN
-THANKS BRYAN. HOW ARE YOY BRO?
-BRYAN: SORRY GUYS, I HAVE TO GO. TALK TO U LATER.
-HIMANSHU: BYE BRYAN
-BYE BRYAN
-BRYAN has exit the chat
