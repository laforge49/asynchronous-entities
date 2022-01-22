from [11200-2operatingModes](11200-2operatingModes.md)
# 11210 Asynchronous Mode
The default operation mode of [11000-gems](11000-gems.md) is asynchronous. Requests are received by a gem as a message. This is implemented using [Clojure core.async](https://clojure.org/news/2013/06/28/clojure-clore-async-channels).

Requests are processed as a stack of sequences, where a sequence of requests gets pushed onto the stack for processing on completion of the current request.

Multiple stacks of sequences are supported as a means of multi-threading.

Within the context of a gem, the gem's name is associated with a port which is used to send request messages to that gem. Request messages are read from this port as long as a gem is in asynchronous mode and is not busy processing another request.
