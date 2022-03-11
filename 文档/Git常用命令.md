# Git常用命令

## 参考地址

> https://git-scm.com/book/en/v2

## 常用命令介绍

1. git init 项目初始化
2. git clone http://url 克隆代码
3. 提交代码 git add . 添加文件至git版本系统 git commit -m "注释" 提交代码至本地系统 git push 提交代码至远程仓库
4. git status 获取文件状态
5. git branch branch_name 创建分支
6. git checkout branch_name 切换分支
7. 当前分支 master 分支2名称 bran2 需求:将bran2合并至master 切换至分支 master 在master中合并分支 git checkout master git merge bran2
8. 删除本地分支 git branch -d branch_name 删除远程分支 git push origin --delete branch_name
9. 提交代码至远程分支 git push --set-upstream origin branch_name