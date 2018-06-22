import re

f= open("haha.markdown","r")

title=f.readline()
content=f.readlines()
images=[] # contains images starting with "!"
content1=content # images replaced with some type of "LFWXt9xURu9EKjlKy_D]"
image_urls=[] # contains image urls
content2=[]



for i in content:
	obj=re.match(r"!(.)*", i)
	if obj:
		images.append(obj.group())


for ele in range(len(content1)):
	if content1[ele][0] == "!":
		for i in range(len(images)):
			if content1[ele]==images[i]:
				content1[ele]="[[-"+str(i)+"LFWXt9xURu9EKjlKy_D]]"

for i in images:
	obj=re.search(r"http(.)*png",i)
	if obj:
		image_urls.append(obj.group())

i=0
while(i<len(content)):
	if len(content[i])>=3:
		#obj=re.match("\'\'\'",content[i])
		if content1[i][0]==content1[i][1]==content1[i][2]=="`":
			print("one")
			j=i+1
			while(j<len(content)):
				if len(content[j])>=3:
					#obj=re.match(r"'''",content[j])
					if content1[j][0]==content1[j][1]==content1[j][2]=="`":
						s=""
						for k in range(i+1,j):
							s=s+content[k]
						print(s)
						i=j+1;content2.append(s)
					else:
						j=j+1
				else:
					j=j+1
		else:
			i=i+1
	else:
		i=i+1
print(content2)
#print(image_urls)
f.close()















