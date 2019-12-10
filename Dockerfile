FROM centos:centos7

RUN yum install git -y \
&& wget https://dl.google.com/go/go1.13.3.linux-amd64.tar.gz \
&& tar -xzf go1.13.3.linux-amd64.tar.gz \
&& mv go /usr/local \
&& export GOROOT=/usr/local/go \ 
&& export PATH=$GOPATH/bin:$GOROOT  \ 
&& git clone https://github.com/filewalkwithme/go-pg-crud.git

WORKDIR go-pg-crud
RUN  go build . \
CMD ./go-pg-crud
