# DevOps Notes

## Amazon Route 53 Domain Name System (DNS) Service:

Using an existing Hosted Zone (i.e., rgcoding.com), we created the following Record Sets:

- Name: vlol.rgcoding.com
- Type: A
- Value: 192.185.7.14
- Name: www.vlol.rgcoding.com
- Type: CNAME
- Value: vlol.rgcoding.com

You must create the Record Sets in order to apply SSL/TLS certificates.

## HostGator Website Hosting Service:

Using an existing domain (i.e., rgcoding.com), we added a subdomain with the following attributes:

- Domain name: vlol.rgcoding.com
- Location of the website's files: rgcoding.com
- Hosting type: Website hosting
- Activate the DNS service: Yes
- Activate the mail service: Yes
- Document root: vlol.rgcoding.com
- Preffered domain: vlol.rgcoding.com

We also secured the domain with a Let's Encrypt certificate authority (CA), which provides a free SSL/TLS certificate the your domain.

We also added a shared FTP account:

- FTP account name: VLOL
- Home directory: /vlol.rgcoding.com
- New password: [Sent via separate email]
- Confirm password: [Sent via separate email]
- Read permission: Yes
- Write permission: Yes


