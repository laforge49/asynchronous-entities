from [11150-NAME](11150-NAME.md)
# 11152 Gem Type

The gem type is determined by what a gem does:

- class - A class gem is used to define other gems.
- classifier - A classifier is used to define a set of gems.
- context - A context gem provides a context in which other gems operate.
- descriptor - A descriptor defines the behavior of a gem.
- facet - A [11100-FACET](11100-FACETS.md) names one of the structures of the data held by gems.
- federator - A federator gem coordinates the behavior of a set of gems which are interoperating synchronously.
- param - A param names one of the arguments of a request.
- request - A request identifies an operation to be performed. It includes the gem to be operated on, the requestid of the operation and the parameters for that operation.
- requestid - A requestid identifies the type of operation to be performed, though the operation can be specific to the gem being operated on.