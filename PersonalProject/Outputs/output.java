@startuml
class A{
+a:int
-b:B
+sampleMethod(s:String):void
-anotherMethod(x:int,y:int):void
}
class B{
-k:int
+hello():void
}
interface I{
}
class J{
+method():void
}
class A--class B
class I<|..interface A
class J<|--class B
@enduml
