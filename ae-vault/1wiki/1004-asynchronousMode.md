from [1003-gems](1003-gems.md)
# 1004 Asynchronous Mode
The default operation mode of [1003-gems](1003-gems.md) is asynchronous. Requests are received by an entity as a message and, on completion of that request, a response is returned. This is implemented using [Clojure core.async](https://clojure.org/news/2013/06/28/clojure-clore-async-channels).

Within the context of an entity, the entity's name is associated with a port which is used to send request messages to that entity. Request messages are read from this port as long as an entity is in asynchronous mode and is not busy processing another request.