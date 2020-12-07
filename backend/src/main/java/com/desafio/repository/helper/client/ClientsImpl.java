package com.desafio.repository.helper.client;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import com.desafio.model.Client;
import com.desafio.model.Client_;
import com.desafio.repository.filter.ClientFilter;

//import com.desafio.model.Client;
//import com.desafio.model.Client_;
//import com.desafio.repository.filter.ClientFilter;

public class ClientsImpl implements ClientsQueries{

	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Client> filtrar(ClientFilter filterParams, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
		Root<Client> root = criteria.from(Client.class);
		
		//	Ordenação
		adicionarOrdenacao(criteria, pageable, builder, root);
		
		//	Adiciona filtros
		Predicate[] predicatesArray = criarRestricoes(filterParams, builder, root);
		
		criteria.where(predicatesArray);
		
		// Método de Paginação
		TypedQuery<Client> query = manager.createQuery(criteria);
		adicionarPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(filterParams));
	}

	// Retorna o total de paginas
	private Long total(ClientFilter filterParams) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Client> root = criteria.from(Client.class);
		
		Predicate[] predicates = criarRestricoes(filterParams, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	//	Adiciona Ordenacao
	private void adicionarOrdenacao(CriteriaQuery<Client> criteria, Pageable pageable, CriteriaBuilder builder,
			Root<Client> root) {
		
		Sort sort = pageable.getSort();
		
		if(sort.isSorted()) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.orderBy(order.isAscending() ? builder.asc(root.get(property)) : builder.desc(root.get(property)));
		}
		
	}
	
	//	Cria Resticoes
	private Predicate[] criarRestricoes(ClientFilter filterParams, CriteriaBuilder builder, Root<Client> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		if (filterParams != null) {
			if (!StringUtils.isEmpty(filterParams.getName())) {
				predicates.add(builder.like(root.get(Client_.name), "%" + filterParams.getName() + "%"));
			}
			if (!StringUtils.isEmpty(filterParams.getCpf())) {
				predicates.add(builder.like(root.get(Client_.cpf), "%" + filterParams.getCpf() + "%"));
			}
		}	
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	// Método de Paginação
	private void adicionarPaginacao(TypedQuery<Client> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPaginacao = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPaginacao;
		
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPaginacao);
		
	}
	
}
