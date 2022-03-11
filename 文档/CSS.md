#布局
##display布局
> 参考文章 https://www.cnblogs.com/Ry-yuan/p/6848197.html
##布局
inline:
使元素变成行内元素，拥有行内元素的特性，即可以与其他行内元素共享一行，不会独占一行.
不能更改元素的height，width的值，大小由内容撑开.
可以使用padding，margin的left和right产生边距效果，但是top和bottom就不行.
block:
使元素变成块级元素，独占一行，在不设置自己的宽度的情况下，块级元素会默认填满父级元素的宽度.
能够改变元素的height，width的值.
可以设置padding，margin的各个属性值，top，left，bottom，right都能够产生边距效果.
inline-block:
结合了inline与block的一些特点，结合了上述inline的第1个特点和block的第2,3个特点.
用通俗的话讲，就是不独占一行的块级元素。
##清除浮动
clear: both;