package com.snake.web.boot.module.rup.util;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

/**
 * 最令人兴奋的是DOM4J对Visitor的支持，
 * 这样可以大大缩减代码量，并且清楚易懂。
 * 了解设计模式的人都知道，Visitor是GOF设计模式之一。
 * 其主要原理就是两种类互相保有对方的引用，
 * 并且一种作为Visitor去访问许多Visitable。
 * 我们来看DOM4J中的Visitor模式(快速文档中没有提供)
 * 只需要自定一个类实现Visitor接口即可。
 * 调用： root.accept(new MyVisitor())
 * Visitor接口提供多种Visit()的重载，根据XML不同的对象，
 * 将采用不同的方式来访问。上面是给出的Element和Attribute的简单实现，
 * 一般比较常用的就是这两个。
 * VisitorSupport是DOM4J提供的默认适配器，Visitor接口的Default Adapter模式，
 * 这个模式给出了各种visit(*)的空实现，以便简化代码。
 * 注意，这个Visitor是自动遍历所有子节点的。
 * 如果是root.accept(MyVisitor)，将遍历子节点。
 *
 * @author Administrator
 */
public class Dom4jVisitorUtil extends VisitorSupport {
    public void visit(Element element) {
        System.out.println(element.getName());
    }

    public void visit(Attribute attr) {
        System.out.println(attr.getName());
    }
}