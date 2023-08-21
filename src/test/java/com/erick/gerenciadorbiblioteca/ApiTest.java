package com.erick.gerenciadorbiblioteca;

import com.erick.gerenciadorbiblioteca.model.Emprestimo;
import com.erick.gerenciadorbiblioteca.model.Livro;
import com.erick.gerenciadorbiblioteca.model.Usuario;
import com.erick.gerenciadorbiblioteca.repository.EmprestimoRepository;
import com.erick.gerenciadorbiblioteca.repository.LivroRepository;
import com.erick.gerenciadorbiblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {

    private final MockMvc mockMvc;
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRespository;
    private final EmprestimoRepository emprestimoRepository;

    @Autowired
    public ApiTest(MockMvc mockMvc, LivroRepository livroRepository, UsuarioRepository usuarioRespository, EmprestimoRepository emprestimoRepository) {
        this.mockMvc = mockMvc;
        this.livroRepository = livroRepository;
        this.usuarioRespository = usuarioRespository;
        this.emprestimoRepository = emprestimoRepository;
    }

    @Test
    public void testPostLivros() throws Exception {
        mockMvc.perform(post("/api/v1/livros")
                        .contentType("application/json")
                        .content("{\"titulo\":\"Livro 1\", \"autor\":\"Autor 1\" }"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetLivros() throws Exception {
        mockMvc.perform(get("/api/v1/livros"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetLivrosById() throws Exception {
        Livro livro = new Livro();
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setAutor("J.K. Rowling");

        Livro livroSalvo = livroRepository.save(livro);

        Long id = livroSalvo.getId();
        
        mockMvc.perform(get("/api/v1/livros/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testGetLivrosPesquisa() throws Exception {
        Livro livro = new Livro();
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setAutor("J.K. Rowling");

        livroRepository.save(livro);
        mockMvc.perform(get("/api/v1/livros?pesquisa=Harry Potter"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }


    @Test
    public void testPutLivros() throws Exception {
        Livro livro = new Livro();
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setAutor("J.K. Rowling");

        Livro livroSalvo = livroRepository.save(livro);

        Long id = livroSalvo.getId();

        mockMvc.perform(put("/api/v1/livros/{id}", id)
                        .contentType("application/json")
                        .content("{\"titulo\":\"As Cronicas de Narnia\", \"autor\":\"C.S. Lewis\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteLivros() throws Exception {
        Livro livro = new Livro();
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setAutor("J.K. Rowling");

        Livro livroSalvo = livroRepository.save(livro);

        Long id = livroSalvo.getId();

        mockMvc.perform(delete("/api/v1/livros/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetLivrosEmprestimos() throws Exception {
        Livro livro = new Livro();
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setAutor("J.K. Rowling");

        Livro livroSalvo = livroRepository.save(livro);

        Long id = livroSalvo.getId();

        mockMvc.perform(get("/api/v1/livros/{id}/emprestimos", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetLivrosEmprestimosDevolvidos() throws Exception {
        Livro livro = new Livro();
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setAutor("J.K. Rowling");

        Livro livroSalvo = livroRepository.save(livro);

        Long id = livroSalvo.getId();

        mockMvc.perform(get("/api/v1/livros/{id}/emprestimos/devolvidos", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testPostUsuarios() throws Exception {
        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType("application/json")
                        .content("{\"nome\":\"Hermione\", \"email\":\"hermione@email.com\", \"telefone\":\"4899999934\" }"))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(post("/api/v1/usuarios")
                        .contentType("application/json")
                        .content("{\"nome\":\"Hermione\", \"email\":\"hermione@email.com\", \"telefone\":\"4899999934\" }"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetUsuarios() throws Exception {
        mockMvc.perform(get("/api/v1/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetUsuariosPesquisa() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Ronnie");
        usuario.setTelefone("4899999936");
        usuario.setEmail("ronnie@mail.com");
        usuarioRespository.save(usuario);

        mockMvc.perform(get("/api/v1/usuarios?pesquisa=Ronnie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetUsuarioById() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Voldemort");
        usuario.setTelefone("4899999936");
        usuario.setEmail("voldemort@mail.com");
        Usuario usuarioSalvo = usuarioRespository.save(usuario);
        Long id = usuarioSalvo.getId();
        mockMvc.perform(get("/api/v1/usuarios/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testPutUsuarios() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Sirius Black");
        usuario.setTelefone("4899999936");
        usuario.setEmail("sirius@mail.com");
        Usuario usuarioSalvo = usuarioRespository.save(usuario);
        Long id = usuarioSalvo.getId();
        mockMvc.perform(put("/api/v1/usuarios/{id}", id)
                        .contentType("application/json")
                        .content("{\"nome\":\"Black Sirius\", \"email\":\"blacksirius@email.com\", \"telefone\":\"48998899889\" }"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testDeleteUsuarios() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Another User");
        usuario.setTelefone("4899999936");
        usuario.setEmail("another@mail.com");
        Usuario usuarioSalvo = usuarioRespository.save(usuario);
        Long id = usuarioSalvo.getId();
        mockMvc.perform(delete("/api/v1/usuarios/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetUsuariosEmprestimos() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Teste 234");
        usuario.setTelefone("4899999936");
        usuario.setEmail("usuario234@mail.com");
        Usuario usuarioSalvo = usuarioRespository.save(usuario);
        Long id = usuarioSalvo.getId();

        mockMvc.perform(get("/api/v1/usuarios/{id}/emprestimos", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetUsuariosEmprestimosAtivos() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Teste 235");
        usuario.setTelefone("4899999936");
        usuario.setEmail("usuario235@mail.com");
        Usuario usuarioSalvo = usuarioRespository.save(usuario);
        Long id = usuarioSalvo.getId();
        mockMvc.perform(get("/api/v1/usuarios/{id}/emprestimos/ativos", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetUsuariosEmprestimosDevolvidos() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Teste 236");
        usuario.setTelefone("4899999936");
        usuario.setEmail("usuario236@mail.com");
        Usuario usuarioSalvo = usuarioRespository.save(usuario);
        Long id = usuarioSalvo.getId();
        mockMvc.perform(get("/api/v1/usuarios/{id}/emprestimos/devolvidos", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetEmprestimos() throws Exception {
        mockMvc.perform(get("/api/v1/emprestimos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetEmprestimosPesquisa() throws Exception {
        mockMvc.perform(get("/api/v1/emprestimos?pesquisa=Harry"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetEmprestimoById() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Teste 242");
        usuario.setTelefone("4899999936");
        usuario.setEmail("usuario242@mail.com");
        Usuario usuarioSalvo = usuarioRespository.save(usuario);
        Long idUsuario = usuarioSalvo.getId();

        Livro livro = new Livro();
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setAutor("J.K. Rowling");
        Livro livroSalvo = livroRepository.save(livro);
        Long idLivro = livroSalvo.getId();

        Emprestimo emprestimo = new Emprestimo(usuarioRespository.findById(idUsuario).get(), livroRepository.findById(idLivro).get());
        emprestimo.setDataEmprestimo(LocalDate.now());
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);
        Long id = emprestimoSalvo.getId();

        mockMvc.perform(get("/api/v1/emprestimos/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void testGetEmprestimosDevolvidos() throws Exception {
        mockMvc.perform(get("/api/v1/emprestimos/devolvidos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetEmprestimosAtivos() throws Exception {
        mockMvc.perform(get("/api/v1/emprestimos/ativos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testPostEmprestimos() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Teste 250");
        usuario.setTelefone("4899999936");
        usuario.setEmail("usuario250@mail.com");
        Usuario usuarioSalvo = usuarioRespository.save(usuario);
        Long idUsuario = usuarioSalvo.getId();

        Livro livro = new Livro();
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setAutor("J.K. Rowling");
        Livro livroSalvo = livroRepository.save(livro);
        Long idLivro = livroSalvo.getId();

        mockMvc.perform(post("/api/v1/emprestimos")
                        .contentType("application/json")
                        .content("{\"livro\":" + idLivro + ", \"usuario\":" + idUsuario + " }"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPatchEmprestimos() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Teste 299");
        usuario.setTelefone("4899999936");
        usuario.setEmail("usuario299@mail.com");
        Usuario usuarioSalvo = usuarioRespository.save(usuario);
        Long idUsuario = usuarioSalvo.getId();

        Livro livro = new Livro();
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setAutor("J.K. Rowling");
        Livro livroSalvo = livroRepository.save(livro);
        Long idLivro = livroSalvo.getId();

        Emprestimo emprestimo = new Emprestimo(usuarioRespository.findById(idUsuario).get(), livroRepository.findById(idLivro).get());
        emprestimo.setDataEmprestimo(LocalDate.now());
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);
        Long idEmprestimo = emprestimoSalvo.getId();

        mockMvc.perform(patch("/api/v1/emprestimos/{id}", idEmprestimo)
                .contentType("application/json")
                .content(""))
                .andExpect(status().is2xxSuccessful());
    }
}
