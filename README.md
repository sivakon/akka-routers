# Different types of routers and routing strategies

*RouterApp* is a implementation of complete vanilla router

## Routers (pool vs group)
- *POOL* - create routees from actorRef
- *GROUP* - create routees from actorPath (sometimes this is easy)

## Strategies
- Round robin
- Balancing
- Smallest mailbox router
- Broadcast router
- Scatter Gather
- Consistent hashing router
- Tail chopping router (randomly choose an actor and sends the message, after sometime it sends the same message to another actor




## application.conf
- Best practice
