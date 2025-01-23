/****
SQL pour la base de données de AgImmo
****/

USE [AgImmo]
GO
/****** Object:  Table [dbo].[Agent]    Script Date: 21/11/2024 15:11:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Agent](
	[agentIdt] [smallint] IDENTITY(1,1) NOT NULL,
	[agentName] [varchar](64) NULL,
	[agentFirstName] [varchar](64) NULL,
	[agentMobile] [varchar](24) NULL,
	[agentPhone] [varchar](24) NULL,
	[agentEmail] [varchar](128) NULL,
	[agentCivility] [int] NOT NULL,
	[agentType] [int] NOT NULL,
	[agentLogin] [varchar](64) NULL,
	[agentPwd] [varchar](255) NULL,
	[agentImage] [varchar](255) NULL,
 CONSTRAINT [PK__Agent__275B3DC67454EE55] PRIMARY KEY CLUSTERED 
(
	[agentIdt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Civility]    Script Date: 21/11/2024 15:11:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Civility](
	[civilityIdt] [int] IDENTITY(1,1) NOT NULL,
	[civilityLbl] [varchar](64) NULL,
	[civilityLbc] [varchar](32) NULL,
 CONSTRAINT [PK__Civility__155E5AE2ED735F3C] PRIMARY KEY CLUSTERED 
(
	[civilityIdt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Fenetres]    Script Date: 21/11/2024 15:11:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Fenetres](
	[fenetreIdt] [int] IDENTITY(1,1) NOT NULL,
	[fenetreKey] [varchar](50) NOT NULL,
	[fenetreLocation] [varchar](128) NOT NULL,
	[fenetreStyleSheet] [varchar](128) NOT NULL,
	[fenetreStageTitle] [varchar](64) NOT NULL,
	[fenetreStageIcone] [varchar](64) NOT NULL,
	[fenetreLoaderMsgErreur] [varchar](256) NOT NULL,
 CONSTRAINT [PK__Fenetres__A47FE8C323C6699C] PRIMARY KEY CLUSTERED 
(
	[fenetreIdt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InfoDetail]    Script Date: 21/11/2024 15:11:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InfoDetail](
	[infoDetailIdt] [int] IDENTITY(1,1) NOT NULL,
	[infoEnteteIdt] [int] NOT NULL,
	[infoDetailKeyIdt] [smallint] NOT NULL,
	[infoDetailDescription] [varchar](128) NOT NULL,
	[infoDetailLbl] [varchar](256) NOT NULL,
	[infoDetailLbc] [varchar](32) NOT NULL,
	[infoDetailValueInt] [int] NOT NULL,
	[infoDetailValueDouble] [decimal](18, 2) NOT NULL,
	[infoDetailTri] [int] NOT NULL,
	[infoDetailDefault] [bit] NOT NULL,
 CONSTRAINT [PK__InfoDeta__97D92167B328663E] PRIMARY KEY CLUSTERED 
(
	[infoDetailIdt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[InfoEntete]    Script Date: 21/11/2024 15:11:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InfoEntete](
	[infoEnteteIdt] [int] IDENTITY(1,1) NOT NULL,
	[infoEnteteKey] [varchar](64) NOT NULL,
	[infoEnteteDescription] [varchar](512) NOT NULL,
	[infoEnteteCbx] [varchar](32) NOT NULL,
	[infoEnteteNbreDetailMax] [int] NOT NULL,
 CONSTRAINT [PK__InfoEnte__D8D67935FB1154A1] PRIMARY KEY CLUSTERED 
(
	[infoEnteteIdt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TypeAgent]    Script Date: 21/11/2024 15:11:24 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TypeAgent](
	[typeAgentIdt] [int] IDENTITY(1,1) NOT NULL,
	[typeAgentLbl] [varchar](64) NULL,
	[typeAgentLbc] [varchar](32) NULL,
 CONSTRAINT [PK__TypeAgent__155E5AE2ED735F3C] PRIMARY KEY CLUSTERED 
(
	[typeAgentIdt] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Agent] ON 
GO
INSERT [dbo].[Agent] ([agentIdt], [agentName], [agentFirstName], [agentMobile], [agentPhone], [agentEmail], [agentCivility], [agentType], [agentLogin], [agentPwd], [agentImage]) VALUES (1, N'CLAIR', N'Benjamin', N'06-16-88-02-61', N'04-76-22-02-61', N'benjamin.clair@yahoo.fr', 1, 3, N'bclair', N'Azerty1!', N'CLAIR Benjamin.png')
GO
INSERT [dbo].[Agent] ([agentIdt], [agentName], [agentFirstName], [agentMobile], [agentPhone], [agentEmail], [agentCivility], [agentType], [agentLogin], [agentPwd], [agentImage]) VALUES (2, N'FAIDI', N'Karim', N'06-75-19-16-44L', N'04-76-21-19-04', N'k.faidi@gmail.com', 1, 4, N'kfaidi', N'Azerty1!', N'FAIDI Karim.png')
GO
INSERT [dbo].[Agent] ([agentIdt], [agentName], [agentFirstName], [agentMobile], [agentPhone], [agentEmail], [agentCivility], [agentType], [agentLogin], [agentPwd], [agentImage]) VALUES (3, N'VIAL', N'Laetitia', N'06-53-09-09-29', N'04-76-21-09-29', N'laetitia.vial@gmail.com', 2, 2, N'lvial', N'Azerty1!', N'VIAL Laetitia.png')
GO
INSERT [dbo].[Agent] ([agentIdt], [agentName], [agentFirstName], [agentMobile], [agentPhone], [agentEmail], [agentCivility], [agentType], [agentLogin], [agentPwd], [agentImage]) VALUES (4, N'LAMBERT', N'Arthur', N'06-90-89-09-77', N'04-76-21-09-77', N'arthur.lambert@free.fr', 1, 1, N'alambert', N'Azerty1!', N'LAMBERT Arthur.png')
GO
INSERT [dbo].[Agent] ([agentIdt], [agentName], [agentFirstName], [agentMobile], [agentPhone], [agentEmail], [agentCivility], [agentType], [agentLogin], [agentPwd], [agentImage]) VALUES (5, N'FRESNE', N'Eleonore', N'06-52-72-75-34', N'04-76-21-75-34', N'e.fresne@yahoo.fr', 2, 1, N'efresne', N'Azerty1!', N'FRESNE Eleonore.png')
GO
INSERT [dbo].[Agent] ([agentIdt], [agentName], [agentFirstName], [agentMobile], [agentPhone], [agentEmail], [agentCivility], [agentType], [agentLogin], [agentPwd], [agentImage]) VALUES (6, N'PIROUET', N'Catherine', N'06-66-23-16-81', N'04-76-21-16-81', N'catherine.pirouet@laposte.net', 2, 1, N'cpirouet', N'Azerty1!', N'PIROUET Catherine.png')
GO
INSERT [dbo].[Agent] ([agentIdt], [agentName], [agentFirstName], [agentMobile], [agentPhone], [agentEmail], [agentCivility], [agentType], [agentLogin], [agentPwd], [agentImage]) VALUES (7, N'AUTIER', N'Carole', N'06-32-26-78-95', N'04-76-21-78-95', N'carole.autier@gmail.com', 2, 1, N'cautier', N'Azerty1!', N'AUTIER Carole.png')
GO
INSERT [dbo].[Agent] ([agentIdt], [agentName], [agentFirstName], [agentMobile], [agentPhone], [agentEmail], [agentCivility], [agentType], [agentLogin], [agentPwd], [agentImage]) VALUES (8, N'MARTIN', N'Sébastien', N'06-39-96-62-79', N'04-76-21-62-79', N's.martin@orangre.fr', 1, 1, N'smartin', N'Azerty1!', N'MARTIN Sebastien.png')
GO
INSERT [dbo].[Agent] ([agentIdt], [agentName], [agentFirstName], [agentMobile], [agentPhone], [agentEmail], [agentCivility], [agentType], [agentLogin], [agentPwd], [agentImage]) VALUES (9, N'MARTIN', N'Victor', N'06-77-12-39-27', N'04-76-21-39-27', N'victor.martin@gmail.com', 1, 2, N'vmartin', N'Azerty1!', N'MARTIN Victor.png')
GO
INSERT [dbo].[Agent] ([agentIdt], [agentName], [agentFirstName], [agentMobile], [agentPhone], [agentEmail], [agentCivility], [agentType], [agentLogin], [agentPwd], [agentImage]) VALUES (10, N'LANG', N'Yvon', N'06-13-71-01-51', N'04-76-21-01-51', N'y.lang@yahoo.fr', 1, 1, N'ylang', N'Azerty1!', N'LANG Yvon.png')
GO
SET IDENTITY_INSERT [dbo].[Agent] OFF
GO
SET IDENTITY_INSERT [dbo].[Civility] ON 
GO
INSERT [dbo].[Civility] ([civilityIdt], [civilityLbl], [civilityLbc]) VALUES (1, N'Monsieur', N'M..')
GO
INSERT [dbo].[Civility] ([civilityIdt], [civilityLbl], [civilityLbc]) VALUES (2, N'Madame', N'Mme')
GO
SET IDENTITY_INSERT [dbo].[Civility] OFF
GO
SET IDENTITY_INSERT [dbo].[Fenetres] ON 
GO
INSERT [dbo].[Fenetres] ([fenetreIdt], [fenetreKey], [fenetreLocation], [fenetreStyleSheet], [fenetreStageTitle], [fenetreStageIcone], [fenetreLoaderMsgErreur]) VALUES (1, N'AgentDefinition', N'/views/AgentDefinition.fxml', N'/style/application.css', N'Ag''immo - Données de l''agent', N'file:src/images/agent.jpg', N'Erreur d''appel de la fenêtre de modification d''un agent')
GO
INSERT [dbo].[Fenetres] ([fenetreIdt], [fenetreKey], [fenetreLocation], [fenetreStyleSheet], [fenetreStageTitle], [fenetreStageIcone], [fenetreLoaderMsgErreur]) VALUES (2, N'Dashboard', N'/views/Dashboard.fxml', N'/style/application.css', N'Ag''immo - Tableau de bord', N'file:src/images/icone_accueil.png', N'Erreur d''appel du tableau de bord ')
GO
INSERT [dbo].[Fenetres] ([fenetreIdt], [fenetreKey], [fenetreLocation], [fenetreStyleSheet], [fenetreStageTitle], [fenetreStageIcone], [fenetreLoaderMsgErreur]) VALUES (3, N'ControleFermeture', N'/views/ControleFermeture.fxml', N'/style/application.css', N'Ag''immo - contrôle de fermeture', N'file:src/images/icone_login.png', N'Erreur d''appel de la fenêtre de fermeture')
GO
INSERT [dbo].[Fenetres] ([fenetreIdt], [fenetreKey], [fenetreLocation], [fenetreStyleSheet], [fenetreStageTitle], [fenetreStageIcone], [fenetreLoaderMsgErreur]) VALUES (4, N'Login', N'/views/Login.fxml', N'/style/application.css', N'Ag''immo - Agence immobilière', N'file:src/images/icone_login.png', N'Erreur d''appel de la fenêtre de Login')
GO
SET IDENTITY_INSERT [dbo].[Fenetres] OFF
GO
SET IDENTITY_INSERT [dbo].[InfoDetail] ON 
GO
INSERT [dbo].[InfoDetail] ([infoDetailIdt], [infoEnteteIdt], [infoDetailKeyIdt], [infoDetailDescription], [infoDetailLbl], [infoDetailLbc], [infoDetailValueInt], [infoDetailValueDouble], [infoDetailTri], [infoDetailDefault]) VALUES (1, 1, 1, N'Portraits', N'E:/Documents/AgImmo/Agents', N'DossierPortraits', 0, CAST(0.00 AS Decimal(18, 2)), 1, 0)
GO
INSERT [dbo].[InfoDetail] ([infoDetailIdt], [infoEnteteIdt], [infoDetailKeyIdt], [infoDetailDescription], [infoDetailLbl], [infoDetailLbc], [infoDetailValueInt], [infoDetailValueDouble], [infoDetailTri], [infoDetailDefault]) VALUES (2, 1, 2, N'ModelesWord', N'E:/Documents/AgImmo/Modeles/Word', N'DossierWord', 0, CAST(0.00 AS Decimal(18, 2)), 7, 0)
GO
INSERT [dbo].[InfoDetail] ([infoDetailIdt], [infoEnteteIdt], [infoDetailKeyIdt], [infoDetailDescription], [infoDetailLbl], [infoDetailLbc], [infoDetailValueInt], [infoDetailValueDouble], [infoDetailTri], [infoDetailDefault]) VALUES (3, 1, 3, N'Documents', N'E:/Documents/AgImmo/Documents/', N'DossiersDoc', 0, CAST(0.00 AS Decimal(18, 2)), 8, 0)
GO
INSERT [dbo].[InfoDetail] ([infoDetailIdt], [infoEnteteIdt], [infoDetailKeyIdt], [infoDetailDescription], [infoDetailLbl], [infoDetailLbc], [infoDetailValueInt], [infoDetailValueDouble], [infoDetailTri], [infoDetailDefault]) VALUES (4, 2, 1, N'TypeAgent', N'Valeur 1 : Agent immobilier', N'Agent Immobilier', 1, CAST(0.00 AS Decimal(18, 2)), 19, 0)
GO
SET IDENTITY_INSERT [dbo].[InfoDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[InfoEntete] ON 
GO
INSERT [dbo].[InfoEntete] ([infoEnteteIdt], [infoEnteteKey], [infoEnteteDescription], [infoEnteteCbx], [infoEnteteNbreDetailMax]) VALUES (1, N'Dossiers', N'Liste des dossiers de l''application', N' ', 0)
GO
INSERT [dbo].[InfoEntete] ([infoEnteteIdt], [infoEnteteKey], [infoEnteteDescription], [infoEnteteCbx], [infoEnteteNbreDetailMax]) VALUES (2, N'ValeursDefaut', N'Liste des valeurs par défaut de l''application', N' ', 0)
GO
SET IDENTITY_INSERT [dbo].[InfoEntete] OFF
GO
SET IDENTITY_INSERT [dbo].[TypeAgent] ON 
GO
INSERT [dbo].[TypeAgent] ([typeAgentIdt], [typeAgentLbl], [typeAgentLbc]) VALUES (1, N'Agent immobilier', N'Agent')
GO
INSERT [dbo].[TypeAgent] ([typeAgentIdt], [typeAgentLbl], [typeAgentLbc]) VALUES (2, N'Chef de secteur', N'Chef.Sect')
GO
INSERT [dbo].[TypeAgent] ([typeAgentIdt], [typeAgentLbl], [typeAgentLbc]) VALUES (3, N'Directeur agence', N'Directeur')
GO
INSERT [dbo].[TypeAgent] ([typeAgentIdt], [typeAgentLbl], [typeAgentLbc]) VALUES (4, N'Administrateur', N'admin')
GO
SET IDENTITY_INSERT [dbo].[TypeAgent] OFF
GO
ALTER TABLE [dbo].[Agent]  WITH CHECK ADD  CONSTRAINT [FK_AGENT_CIVILITY] FOREIGN KEY([agentCivility])
REFERENCES [dbo].[Civility] ([civilityIdt])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Agent] CHECK CONSTRAINT [FK_AGENT_CIVILITY]
GO
ALTER TABLE [dbo].[Agent]  WITH CHECK ADD  CONSTRAINT [FK_AGENT_TYPE] FOREIGN KEY([agentType])
REFERENCES [dbo].[TypeAgent] ([typeAgentIdt])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Agent] CHECK CONSTRAINT [FK_AGENT_TYPE]
GO
ALTER TABLE [dbo].[InfoDetail]  WITH CHECK ADD  CONSTRAINT [FK_INFODETAIL_ENTETE] FOREIGN KEY([infoEnteteIdt])
REFERENCES [dbo].[InfoEntete] ([infoEnteteIdt])
GO
ALTER TABLE [dbo].[InfoDetail] CHECK CONSTRAINT [FK_INFODETAIL_ENTETE]
GO
