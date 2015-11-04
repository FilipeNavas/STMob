# IFSP - Semana de Tecnologia Mobile
Projeto Interdisciplinar Semana de Tecnologia Mobile


Estrutura do Projeto no Android Studio  

- app
    - manifest
          - AndroidManifest.xml
    - java
        - br.edu.ifsp.stmob.model
          - Usuario
          - AvisoExtraordinario
          - Atividade
          - Palestrante
          - AreaConhecimento
          - Feedback
          - Inscricao
        - br.edu.ifsp.stmob.activity
          - MainActivity
          - UsuarioActivity
          - AtividadeActivity
          - AvisoExtraordinarioActivity
          - ... muitas outras
        - br.edu.ifsp.stmob.dao
          - UsuarioDao
          - AvisoExtraordinarioDao
          - AtividadeDao
          - PalestranteDao
          - AreaConhecimentoDao
          - FeedbackDao
          - InscricaoDao
        - br.edu.ifsp.stmob.dao.factory
          - ConnectionFactory
        - br.edu.ifsp.stmob.test
          - UsuarioTest
          - AtividadeTest
          - ... muitas outras
    - res
       - drawable
       - layout
         - activity_main.xml
         - activity_usuario.xml
         - activity_atividade.xml
         - ... muitas outras
       - menu
         - menu_main.xml
         - menu_usuario.xml
         - menu_atividade.xml
         - ... muitas outras
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
          
           
    
        
    

