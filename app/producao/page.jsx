'use client';

import { Button, Label, Modal, Select, TextInput } from 'flowbite-react';
import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import React from 'react';
import { Pagination } from 'flowbite-react';

export default function Producao() {
  const [data, setData] = useState(null);
  const [pesquisador, setPesquisador] = useState(null);
  const [tipoProducao, setTipoProducao] = useState(null);
  const [instituto, setInstituto] = useState(null);
  const [producaoList, setProducaoList] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const { register, handleSubmit } = useForm();
  const [pageNumber, setPageNumber] = useState(0);

  function pesquisarProducao({
    dataInicio,
    dataFim,
    instituto,
    pesquisador,
    tipoProducao,
  }) {
    fetch(
      `http://localhost:8080/producao/filtro?dataInicio=${dataInicio}&dataFim=${dataFim}&instituto=${instituto}&pesquisador=${pesquisador}&tipoProducao=${tipoProducao}`
    )
      .then((res) => res.json())
      .then((data) => {
        setData(data);
      })
      .catch((err) => console.log(err));
  }

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/producao?page=${pageNumber}&size=10`)
      .then((res) => res.json())
      .then((data) => {
        setData(data);
        setLoading(false);
      })
      .catch((err) => console.log(err));

    fetch('http://localhost:8080/instituto')
      .then((res) => res.json())
      .then((instituto) => {
        setInstituto(instituto);
        setLoading(false);
      })
      .catch((err) => console.log(err));

    fetch('http://localhost:8080/pesquisador')
      .then((res) => res.json())
      .then((pesquisador) => {
        setPesquisador(pesquisador);
        setLoading(false);
      })
      .catch((err) => console.log(err));

    fetch('http://localhost:8080/tipoProducao')
      .then((res) => res.json())
      .then((tipoProducao) => {
        setTipoProducao(tipoProducao);
        setLoading(false);
      })
      .catch((err) => console.log(err));
  }, [pageNumber]);

  return (
    <div className="mx-2">
      <div className="flex justify-between items-center">
        <h1 className="text-4xl font-bold px-4 py-6">Produção</h1>

        <form
          onSubmit={handleSubmit(pesquisarProducao)}
          className='flex justify-between items-center"'
        >
          <div className="block mr-4">
            <TextInput
              {...register('dataInicio')}
              className="w-23"
              name="dataInicio"
              placeholder="Data Início"
            />
          </div>
          <div className="block mr-4">
            <TextInput
              {...register('dataFim')}
              className="w-23"
              name="dataFim"
              placeholder="Data Fim"
            />
          </div>

          <div className="mr-4" id="select">
            <Select {...register('instituto')} name="instituto" required={true}> {/* temos que olhar esse required pois está obrigando preencher um instituti para fazer a pesquisa */}
              <option value="">Instituto</option>
              {instituto?.content &&
                instituto.content.map((instituto) => (
                  <option value={instituto.nome}>{instituto.nome}</option>
                ))}
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select {...register('pesquisador')} name="pesquisador">
              <option value="">Pesquisador</option>
              {pesquisador?.content &&
                pesquisador.content.map((pesquisador) => (
                  <option value={pesquisador.id}>{pesquisador.nome}</option>
                ))}
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select {...register('tipoProducao')} name="tipoProducao">
              <option value="">Tipo Prod.</option>
              {tipoProducao && tipoProducao.content.map((tipo) => (
                <option key={tipo} value={tipo}>{tipo}</option>
              ))}
            </Select>
          </div>
          <div className="flex justify-between items-center">
            <button
              type="submit"
              className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            >
              Pesquisar
            </button>
          </div>
        </form>
      </div>

      <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead className="text-xs text-gray-700 uppercase bg-gray-300 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" className="px-6 py-3 max-w-sm">
                Titulo
              </th>
              <th scope="col" className="px-6 py-3">
                Ano
              </th>
              <th scope="col" className="px-6 py-3">
                Tipo de Produção
              </th>
            </tr>
          </thead>
          <tbody>
            {data?.content &&
              data.content.map((producao) => (
                <tr
                  key={producao.id}
                  className="bg-white border-b dark:bg-gray-900 dark:border-gray-700"
                >
                  <th
                    scope="row"
                    className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white max-w-sm overflow-hidden"
                  >
                    {producao.nome}
                  </th>
                  <td className="px-6 py-4">{producao.ano}</td>
                  <td className="px-6 py-4">{producao.tipoProducao}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>

      {/* Paginação */}
      <div className="flex items-center justify-center text-center">
        <Pagination
          currentPage={pageNumber}
          layout="pagination"
          nextLabel="Próxima"
          onPageChange={(page) => setPageNumber(page - 1)}
          previousLabel="Anterior"
          showIcons
          totalPages={data && data.totalPages}
        />
      </div>
    </div>
  );
}
