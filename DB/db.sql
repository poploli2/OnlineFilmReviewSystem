SET FOREIGN_KEY_CHECKS=0;
DROP DATABASE IF EXISTS onlinefilmreview;
CREATE DATABASE onlinefilmreview;
USE onlinefilmreview;

DROP TABLE IF EXISTS User;
CREATE TABLE User (
                      UserID INT AUTO_INCREMENT PRIMARY KEY,
                      Username VARCHAR(255) UNIQUE NOT NULL,
                      Password VARCHAR(255) NOT NULL,
                      Approval_Status BOOLEAN DEFAULT FALSE
);

DROP TABLE IF EXISTS Admin;
CREATE TABLE Admin(
                      AdminID INT AUTO_INCREMENT PRIMARY KEY,
                      Adminname VARCHAR(255) UNIQUE NOT NULL,
                      Password VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS Movie;
CREATE TABLE Movie (
                       MovieID INT AUTO_INCREMENT PRIMARY KEY,
                       Title VARCHAR(255) NOT NULL,
                       Director VARCHAR(255),
                       Actor VARCHAR(255),
                       Rating DECIMAL(3, 1),
                       Release_Date DATE,
                       Views INT DEFAULT 0,
                       Popularity INT DEFAULT 0,
                       Introduction TEXT
);

DROP TABLE IF EXISTS Review;
CREATE TABLE Review (
                        ReviewID INT AUTO_INCREMENT PRIMARY KEY,
                        UserID INT,
                        MovieID INT,
                        Content TEXT,
                        Score DECIMAL(3, 1),
                        Timestamp TIMESTAMP,
                        FOREIGN KEY (UserID) REFERENCES User(UserID),
                        FOREIGN KEY (MovieID) REFERENCES Movie(MovieID)
);

DROP TABLE IF EXISTS AccessLog;
CREATE TABLE Access_Log (
                           LogID INT AUTO_INCREMENT PRIMARY KEY,
                           UserID INT,
                           Operation VARCHAR(255),
                           Timestamp TIMESTAMP,
--                            Success BOOLEAN DEFAULT FALSE,
                           FOREIGN KEY (UserID) REFERENCES User(UserID)
);

INSERT INTO User (Username, Password,Approval_Status)
VALUES ('admin', '123456',TRUE);
INSERT INTO Admin(Adminname,Password)
VALUES('admin','123456');
INSERT INTO Movie(MovieID,Title,Release_Date,Introduction,Director,Actor)
VALUES(1,'我和我的祖国','2019-09-30','七位导演分别取材新中国成立70周年以来，祖国经历的无数个历史性经典瞬间。讲述普通人与国家之间息息相关密不可分的动人故事。聚焦大时代大事件下，小人物和国家之间，看似遥远实则密切的关联，唤醒全球华人共同回忆。','陈凯歌','黄渤 / 张译 / 韩昊霖 / 杜江 / 葛优 / 刘昊然 / 宋佳'),
      (2,'千与千寻','2019-06-21','千寻和爸爸妈妈一同驱车前往新家，在郊外的小路上不慎进入了神秘的隧道——他们去到了另外一个诡异世界—一个中世纪的小镇。远处飘来食物的香味，爸爸妈妈大快朵颐，孰料之后变成了猪！这时小镇上渐渐来了许多样子古怪、半透明的人。
　　千寻仓皇逃出，一个叫小白的人救了他，喂了她阻止身体消 失的药，并且告诉她怎样去找锅炉爷爷以及汤婆婆，而且必须获得一份工作才能不被魔法变成别的东西。
　　千寻在小白的帮助下幸运地获得了一份在浴池打杂的工作。渐渐她不再被那些怪模怪样的人吓倒，并从小玲那儿知道了小白是凶恶的汤婆婆的弟子。
　　一次，千寻发现小白被一群白色飞舞的纸人打伤，为了救受伤的小白，她用河神送给她的药丸驱出了小白身体内的封印以及守封印的小妖精，但小白还是没有醒过来。
　　为了救小白，千寻又踏上了她的冒险之旅。','宫崎骏','柊瑠美 / 入野自由 / 夏木真理 / 菅原文太 / 中村彰男'),
      (3,'我不是药神',' 2018-07-05','普通中年男子程勇（徐峥 饰）经营着一家保健品店，失意又失婚。不速之客吕受益（王传君 饰）的到来，让他开辟了一条去印度买药做“代购”的新事业，虽然困难重重，但他在这条“买药之路”上发现了商机，一发不可收拾地做起了治疗慢粒白血病的印度仿制药独家代理商。赚钱的同时，他也认识了几个病患及家属，为救女儿被迫做舞女的思慧（谭卓 饰）、说一口流利“神父腔”英语的刘牧师（杨新鸣 饰），以及脾气暴烈的“黄毛”（章宇 饰），几个人合伙做起了生意，利润倍增的同时也危机四伏。程勇昔日的小舅子曹警官（周一围 饰）奉命调查仿制药的源头，假药贩子张长林（王砚辉 饰）和瑞士正牌医药代表（李乃文 饰）也对其虎视眈眈，生意逐渐变成了一场关于救赎的拉锯战。
　　本片改编自慢粒白血病患者陆勇代购抗癌药的真实事迹。','文牧野','徐峥 / 王传君'),
      (4,'这个杀手不太冷','1994-09-14','里昂（让·雷诺饰）是名孤独的职业杀手，受人雇佣。一天，邻居家小姑娘马蒂尔达（纳塔丽·波特曼饰)敲开他的房门，要求在他那里暂避杀身之祸。原来邻居家的主人是警方缉毒组的眼线，只因贪污了一小包毒品而遭恶警（加里·奥德曼饰）杀害全家的惩罚。马蒂尔达得到里昂的留救，幸免于难，并留在里昂那里。里昂教小女孩使枪，她教里昂法文，两人关系日趋亲密，相处融洽。
　　女孩想着去报仇，反倒被抓，里昂及时赶到，将女孩救回。混杂着哀怨情仇的正邪之战渐次升级，更大的冲突在所难免……','吕克·贝松','让·雷诺 / 娜塔莉·波特曼 / 加里·奥德曼 / 丹尼·爱罗 / 彼得·阿佩尔');
SET FOREIGN_KEY_CHECKS=1;