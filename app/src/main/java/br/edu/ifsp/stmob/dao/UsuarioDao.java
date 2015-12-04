package br.edu.ifsp.stmob.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.stmob.modelo.Usuario;

/**
 * Created by Fernando Navas on 11/11/2015.
 */
public class UsuarioDAO extends DAO<Usuario> {

        private SQLiteDatabase database;



        public UsuarioDAO(Context context) {
            super(context);
            campos = new String[]{"Cod_Usuario","Nome","Email","Senha","Tipo","Telefone"};
            tableName = "Usuario";
            database = getWritableDatabase();

        }


        public Usuario getByEmail(String email) {
            Usuario usu = null;

            Cursor cursor = executeSelect("Email = ?", new String[]{email}, null);

            if(cursor!=null && cursor.moveToFirst())
            {
                usu = serializeByCursor(cursor);
            }
            if(!cursor.isClosed())
            {
                cursor.close();
            }


            return usu;
        }


        public Usuario getByCod(Integer codUsuario)
        {
            Usuario usu = null;

            Cursor cursor = executeSelect("Cod_Usuario = ?", new String[]{String.valueOf(codUsuario)}, null);

            if (cursor != null && cursor.moveToFirst())
            {
                usu = serializeByCursor(cursor);
            }
            if (!cursor.isClosed())
            {
                cursor.close();
            }

            return usu;
        }

        public Usuario getBySenha(String senha) {
            Usuario se = null;

            Cursor cursor = executeSelect("Senha = ?", new String[]{String.valueOf(senha)}, null);

            if(cursor!=null && cursor.moveToFirst())
            {
                se = serializeByCursor(cursor);
            }
            if(!cursor.isClosed())
            {
                cursor.close();
            }


            return se;
        }

        public List<Usuario> listAll() {
            List<Usuario> list = new ArrayList<Usuario>();
            Cursor cursor = executeSelect(null, null, "1");


            if(cursor!=null && cursor.moveToFirst())
            {
                do{
                    list.add(serializeByCursor(cursor));
                }while(cursor.moveToNext());


            }

            if(!cursor.isClosed())
            {
                cursor.close();
            }

            return list;


        }

        public boolean salvar(Usuario usuario) {
            //ContentValues values = serializeContentValues(pessoa);

            //Serializa para ContentValues - nao pega o codigo
            ContentValues values = new ContentValues();

            values.put("Nome", usuario.getUsuNome());
            values.put("Email", usuario.getUsuEmail());
            values.put("Senha", usuario.getUsuSenha());
            values.put("Tipo", usuario.getUsuTipo());
            values.put("Telefone", usuario.getUsuTelefone());

            if(database.insert(tableName, null, values)>0)
                return true;
            else
                return false;
        }

        public boolean deletar(Integer id) {
            if(database.delete(tableName, "Cod_Usuario = ?", new String[]{String.valueOf(id)})>0)
                return true;
            else
                return false;
        }
        // atualizar Participante
        public boolean atualizar(Usuario pessoa) {
            ContentValues values = serializeContentValues(pessoa);
            if(database.update(tableName, values, "Cod_Usuario = ?", new String[]{String.valueOf(pessoa.getUsuCod())})>0)
                return true;
            else
                return false;
        }


        private Usuario serializeByCursor(Cursor cursor)
        {
            Usuario usu = new Usuario();
            usu.setUsuCod(cursor.getInt(0));
            usu.setUsuNome(cursor.getString(1));
            usu.setUsuEmail(cursor.getString(2));
            usu.setUsuSenha(cursor.getString(3));
            usu.setUsuTipo(cursor.getString(4));
            usu.setUsuTelefone(cursor.getString(5));

            return usu;

        }
        // busca o usuário
        private ContentValues serializeContentValues(Usuario usuario)
        {
            ContentValues values = new ContentValues();
            values.put("Cod_Usuario", usuario.getUsuCod());
            values.put("Nome", usuario.getUsuNome());
            values.put("Email", usuario.getUsuEmail());
            values.put("Senha", usuario.getUsuEmail());
            values.put("Tipo", usuario.getUsuTipo());
            values.put("Telefone", usuario.getUsuTelefone());

            return values;
        }

        private Cursor executeSelect(String selection, String[] selectionArgs, String orderBy)
        {

            return database.query(tableName,campos, selection, selectionArgs, null, null, orderBy);

        }




}
