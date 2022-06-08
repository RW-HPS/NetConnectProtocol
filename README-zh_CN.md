## 首先
首先克隆这个存储库。要编辑插件显示名称和其他数据，请查看 plugin.json  
通过进入 settings.gradle 编辑项目本身的名称  

## 基本用法
有关一些基本命令和事件处理程序，请参阅 Main.java  
每个主要插件类都必须扩展插件。确保 plugin.json 指向正确的主插件类  

请注意，插件系统处于测试阶段，因此可能会发生变化  

## 构建一个Jar
gradlew jar or ./gradlew jar

Jar输出在 build/libs.

## 安装
只需将上述步骤中的输出 jar 放在服务器的 data/plugins 目录中，然后重新启动服务器  
通过运行 mods 命令列出您当前安装的插件  
