provider "aws" {
  # Configuration options
  region = "us-east-1"
 
}

#Create Network Setup
#Create a VPC

resource "aws_vpc" "vpc_vpc" {
  cidr_block       = "10.0.0.0/16"
  instance_tenancy = "default"

  tags = {
    Name = "Test_VPC"
  }
}

# Create an internet gateway

resource "aws_internet_gateway" "gateway1" {
  vpc_id = aws_vpc.vpc_vpc.id

  tags = {
    Name = "Test_Gateway"
  }
}

#Create a custom Route Table

resource "aws_route_table" "Route1" {
  vpc_id = aws_vpc.vpc_vpc.id

  route {
    cidr_block = "10.0.1.0/24"
    gateway_id = aws_internet_gateway.gateway1.id
  }

  tags = {
    Name = "Route_table"
  }
}

#Create a Subnet
resource "aws_subnet" "Subnet1" {
  vpc_id     = aws_vpc.vpc_vpc.id
  cidr_block = "10.0.1.0/24"

  tags = {
    Name = "Test_Subnet"
  }
}

#Associate the Subnet with the Route Table

resource "aws_route_table_association" "route_table_associate1" {
  subnet_id      = aws_subnet.Subnet1.id
  route_table_id = aws_route_table.Route1.id

}

#Security Group Setup
#Create a new security group ( Enable ports 22, 80, 443)

resource "aws_security_group" "allow_tls1" {
  name        = "allow_tls"
  description = "Allow TLS inbound traffic and all outbound traffic"
  vpc_id      = aws_vpc.vpc_vpc.id

  tags = {
    Name = "allow_tls"
  }
}

#Create a new security group ( Enable ports 443)

resource "aws_vpc_security_group_ingress_rule" "allow_443_ipv4_1" {
  security_group_id = aws_security_group.allow_tls1.id
  cidr_ipv4         = aws_vpc.vpc_vpc.cidr_block
  from_port         = 443
  ip_protocol       = "https"
  to_port           = 443
}

#Create a new security group ( Enable ports 80)

resource "aws_vpc_security_group_ingress_rule" "allow_80_ipv4_1" {
  security_group_id = aws_security_group.allow_tls1.id
  cidr_ipv4         = aws_vpc.vpc_vpc.cidr_block
  from_port         = 80
  ip_protocol       = "http"
  to_port           = 80
}

#Create a new security group ( Enable ports 22)

resource "aws_vpc_security_group_ingress_rule" "allow_22_ipv4_1" {
  security_group_id = aws_security_group.allow_tls1.id
  cidr_ipv4         = aws_vpc.vpc_vpc.cidr_block
  from_port         = 22
  ip_protocol       = "ssh"
  to_port           = 22
}


resource "aws_instance" "Prod Server" {
  ami = "ami-080e1f13689e07408"
  instance_type = "t2.micro"

   tags = {
    Name = "Project1"
  }
  
}