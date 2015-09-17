# IFSP - Semana de Tecnologia Mobile
Projeto Interdisciplinar Semana de Tecnologia Mobile


Estrutura do Projeto no Android Studio  

- app
    - manifest
          - AndroidManifest.xml
    - java
        - br.edu.ifsp.stmob.model
          - Usuario
          - Atividade
          - Evento
        - br.edu.ifsp.stmob.activity
          - MainActivity
          - UsuarioActivity
          - AtividadeActivity
          - EventoActivity
        - br.edu.ifsp.stmob.dao
          - UsuarioDao
          - AtividadeDao
          - EventoDao
        - br.edu.ifsp.stmob.dao.factory
          - ConnectionFactory
        - br.edu.ifsp.stmob.test
          - UsuarioTest
          - AtividadeTest
          - EventoTest
    - res
       - drawable
       - layout
         - activity_main.xml
         - activity_usuario.xml
         - activity_atividade.xml
         - activity_evento.xml
       - menu
         - menu_main.xml
         - menu_usuario.xml
         - menu_atividade.xml
         - menu_evento.xml
       - mipmap
         - ic_launcher.png
            - ic_launcher.png (hdpi) 
            - ic_launcher.png (mdpi) 
            - ic_launcher.png (xhdpi) 
            - ic_launcher.png (xxhdpi)
       - values
         - dimens.xml
            - dimens.xml
            - dimens.xml (w820dp)
        - strings.xml
        - styles.xml
    - Gradle Scripts
      - build.gradle (Project: StMob)
      - build.gradle (Module: app)
      - proguard-rules.pro (ProGuard Rules for app)
      - gradle.properties (Project Properties)
      - settings.gradle (Project Settings)
      - local.properties (SDK Location)
          
           
    
        
    

