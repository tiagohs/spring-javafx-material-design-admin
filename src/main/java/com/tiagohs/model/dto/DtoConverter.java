package com.tiagohs.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.tiagohs.model.Aluno;
import com.tiagohs.model.CargaHoraria;
import com.tiagohs.model.Disciplina;
import com.tiagohs.model.Professor;
import com.tiagohs.model.Role;
import com.tiagohs.model.Turma;
import com.tiagohs.model.Usuario;

public class DtoConverter {
	
	private SimpleDateFormat dateFormat;
	
	public DtoConverter() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	public void listDtoToListEntity() {
		
	}
	
	public void listEntityToListDto() {
		
	}
	
	public Usuario dtoToEntity(UsuarioDTO usuarioDto) {
		Usuario usuario = new Usuario();
		
		usuario.setId(usuarioDto.getId());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setPassword(usuarioDto.getPassword());
		
		return usuario;
		
	}
	
	public UsuarioDTO entityToDto(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setPassword(usuario.getPassword());
		
		return usuarioDTO;
	}
	
	public Role dtoToEntity(RoleDTO roleDTO) {
		Role role = new Role();
		
		role.setId(roleDTO.getId());
		role.setName(roleDTO.getName());
		role.setRole(roleDTO.getRole());
		
		return role;
	}
	
	public RoleDTO entityToDto(Role role) {
		RoleDTO roleDTO = new RoleDTO();
		
		roleDTO.setId(role.getId());
		roleDTO.setName(role.getName());
		roleDTO.setRole(role.getRole());
		
		return roleDTO;
	}
	
	public Aluno dtoToEntity(AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno();
		
		try {
			aluno.setId(alunoDTO.getId());
			aluno.setMatricula(alunoDTO.getMatricula());
			aluno.setNome(alunoDTO.getNome());
			aluno.setSexo(alunoDTO.getSexo());
			aluno.setDataNascimento(dateFormat.parse(alunoDTO.getDataNascimento()));
		} catch (ParseException e) {
			
		}
		
		return aluno;
	}
	
	public AlunoDTO entityToDto(Aluno aluno) {
		AlunoDTO alunoDTO = new AlunoDTO();
		
		try {
			alunoDTO.setId(aluno.getId());
			alunoDTO.setMatricula(aluno.getMatricula());
			alunoDTO.setNome(aluno.getNome());
			alunoDTO.setSexo(aluno.getSexo());
			alunoDTO.setDataNascimento(dateFormat.format(aluno.getDataNascimento()));
		} catch (Exception e) {
			
		}
		
		return alunoDTO;
	}
	
	public Disciplina dtoToEntity(DisciplinaDTO disciplinaDTO) {
		Disciplina disciplina = new Disciplina();
		
		disciplina.setId(disciplinaDTO.getId());
		disciplina.setDescricao(disciplinaDTO.getDescricao());
		disciplina.setBibliografia(disciplinaDTO.getBibliografia());
		disciplina.setEmenta(disciplinaDTO.getEmenta());
		disciplina.setPreRequisitos(disciplinaDTO.getPreRequisitos());
		
		if (disciplinaDTO.getTeorica() != 0 && disciplinaDTO.getEstagio() != 0 && disciplinaDTO.getPratica() != 0) {
			CargaHoraria cargaHoraria = new CargaHoraria();
			
			cargaHoraria.setEstagio(disciplinaDTO.getEstagio());
			cargaHoraria.setPratica(disciplinaDTO.getPratica());
			cargaHoraria.setTeorica(disciplinaDTO.getTeorica());
			
			disciplina.setCargaHoraria(cargaHoraria);
			cargaHoraria.setDisciplina(disciplina);
		}
		
		return disciplina;
	}
	
	public DisciplinaDTO entityToDto(Disciplina disciplina) {
		DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
		
		disciplinaDTO.setId(disciplina.getId());
		disciplinaDTO.setDescricao(disciplina.getDescricao());
		disciplinaDTO.setBibliografia(disciplina.getBibliografia());
		disciplinaDTO.setEmenta(disciplina.getEmenta());
		disciplinaDTO.setPreRequisitos(disciplina.getPreRequisitos());
		
		if (disciplina.getCargaHoraria() != null) {
			disciplinaDTO.setEstagio(disciplina.getCargaHoraria().getEstagio());
			disciplinaDTO.setPratica(disciplina.getCargaHoraria().getPratica());
			disciplinaDTO.setTeorica(disciplina.getCargaHoraria().getTeorica());
		}
		
		return disciplinaDTO;
	}
	
	public Professor dtoToEntity(ProfessorDTO professorDTO) {
		Professor professor = new Professor();
		
		
		return professor;
	}
	
	public ProfessorDTO entityToDto(Professor professor) {
		ProfessorDTO professorDTO = new ProfessorDTO();
		
		return professorDTO;
	}
	
	public Turma dtoToEntity(TurmaDTO turmaDTO) {
		Turma turma = new Turma();
		
		
		return turma;
	}
	
	public TurmaDTO entityToDto(Turma turma) {
		TurmaDTO turmaDTO = new TurmaDTO();
		
		return turmaDTO;
	}
	
	public List<Usuario> convertListDtoToListUsuarios(List<UsuarioDTO> listUsuariosDTO) {
		List<Usuario> listUsuarios = new ArrayList<>();
		
		for (UsuarioDTO usuarioDTO : listUsuariosDTO) {
			listUsuarios.add(dtoToEntity(usuarioDTO));
		}
		
		return listUsuarios;
	}
	
	public List<UsuarioDTO> convertListToListDtoUsuarios(List<Usuario> listUsuarios) {
		List<UsuarioDTO> listUsuariosDTO = new ArrayList<>();
		
		for (Usuario usuario : listUsuarios) {
			listUsuariosDTO.add(entityToDto(usuario));
		}
		
		return listUsuariosDTO;
	}
	
	public List<Role> convertListDtoToListRoles(List<RoleDTO> listRolesDTO) {
		List<Role> listRoles = new ArrayList<>();
		
		for (RoleDTO roleDTO : listRolesDTO) {
			listRoles.add(dtoToEntity(roleDTO));
		}
		
		return listRoles;
	}
	
	public List<RoleDTO> convertListToListDtoRoles(List<Role> listRoles) {
		List<RoleDTO> listRolesDTO = new ArrayList<>();
		
		for (Role role : listRoles) {
			listRolesDTO.add(entityToDto(role));
		}
		
		return listRolesDTO;
	}
	
	public List<Aluno> convertListDtoToListAlunos(List<AlunoDTO> listAlunosDTO) {
		List<Aluno> listAlunos = new ArrayList<>();
		
		for (AlunoDTO alunoDTO : listAlunosDTO) {
			listAlunos.add(dtoToEntity(alunoDTO));
		}
		
		return listAlunos;
	}
	
	public List<AlunoDTO> convertListToListDtoAlunos(List<Aluno> listAlunos) {
		List<AlunoDTO> listAlunosDTO = new ArrayList<>();
		
		for (Aluno aluno : listAlunos) {
			listAlunosDTO.add(entityToDto(aluno));
		}
		
		return listAlunosDTO;
	}
	
	public List<Disciplina> convertListDtoToListDisciplinas(List<DisciplinaDTO> listDisciplinasDTO) {
		List<Disciplina> listDisciplinas = new ArrayList<>();
		
		for (DisciplinaDTO disciplinaDTO : listDisciplinasDTO) {
			listDisciplinas.add(dtoToEntity(disciplinaDTO));
		}
		
		return listDisciplinas;
	}
	
	public List<DisciplinaDTO> convertListToListDtoDisciplinas(List<Disciplina> listDisciplinas) {
		List<DisciplinaDTO> listDisciplinasDTO = new ArrayList<>();
		
		for (Disciplina disciplina : listDisciplinas) {
			listDisciplinasDTO.add(entityToDto(disciplina));
		}
		
		return listDisciplinasDTO;
	}
	
	public List<Professor> convertListDtoToListProfessores(List<ProfessorDTO> listProfessoresDTO) {
		List<Professor> listProfessores = new ArrayList<>();
		
		for (ProfessorDTO professorDTO : listProfessoresDTO) {
			listProfessores.add(dtoToEntity(professorDTO));
		}
		
		return listProfessores;
	}
	
	public List<ProfessorDTO> convertListToListDtoProfessores(List<Professor> listProfessores) {
		List<ProfessorDTO> listProfessoresDTO = new ArrayList<>();
		
		for (Professor professor : listProfessores) {
			listProfessoresDTO.add(entityToDto(professor));
		}
		
		return listProfessoresDTO;
	}

	public List<Turma> convertListDtoToListTurmas(List<TurmaDTO> listTurmasDTO) {
		List<Turma> listTurmas = new ArrayList<>();
		
		for (TurmaDTO turmaDTO : listTurmasDTO) {
			listTurmas.add(dtoToEntity(turmaDTO));
		}
		
		return listTurmas;
	}
	
	public List<TurmaDTO> convertListToListDtoTurmas(List<Turma> listTurmas) {
		List<TurmaDTO> listTurmasDTO = new ArrayList<>();
		
		for (Turma turma : listTurmas) {
			listTurmasDTO.add(entityToDto(turma));
		}
		
		return listTurmasDTO;
	}
	
	
}
