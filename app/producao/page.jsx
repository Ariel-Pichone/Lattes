'use client';

import { Button, Label, Modal, Select, TextInput } from 'flowbite-react';
import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';

export default function Producao() {
  const [data, setData] = useState(null);
  const [producaoList, setProducaoList] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const { register, handleSubmit } = useForm();

  function pesquisarProducao(data) {
    const termo = data.termo;
    const campo = data.campo;
    let url = '';

    // switch (campo) {
    //   case 'Nome':
    //     url = 'http://localhost:8080/pesquisador/nome/';
    //     break;
    //   case 'Identificador':
    //     url = 'http://localhost:8080/pesquisador/';
    //     break;
    // }

    // fetch(`${url}` + termo)
    //   .then(console.log('pesquisa realizada com o campo' + campo))
    //   .then((res) => res.json())
    //   .then((data) => {
    //     setData(data);
    //   })
    //   .catch((err) => console.log(err));
  }

  useEffect(() => {
    setLoading(true);
    fetch('http://localhost:8080/producao/')
      .then((res) => res.json())
      .then((data) => {
        setData(data);
        setLoading(false);
      })
      .catch((err) => console.log(err));
  }, []);

  if (isLoading) return <p>Loading...</p>;
  if (!data) return <p>No data</p>;

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
              id="termo"
              name="dataInicio"
              placeholder="Data Início"
            />
          </div>
          <div className="block mr-4">
            <TextInput
              {...register('dataFim')}
              className="w-23"
              id="termo"
              name="dataFim"
              placeholder="Data Fim"
            />
          </div>

          <div className="mr-4" id="select">
            <Select
              {...register('instituto')}
              id="camp"
              name="instituto"
              required={true}
            >
              <option>Instituto</option>
              <option>teste</option>
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select
              {...register('pesquisador')}
              id="campo"
              name="pesquisador"
              required={true}
            >
              <option>Pesquisador</option>
              <option>teste</option>
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select
              {...register('tipoProducao')}
              id="campo"
              name="tipoProducao"
              required={true}
            >
              <option>Tipo Prod.</option>
              <option>teste</option>
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
            {data.map((producao) => (
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
    </div>
  );
}