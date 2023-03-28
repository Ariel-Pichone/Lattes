'use client';

import {
  Button,
  Checkbox,
  Label,
  Modal,
  Select,
  TextInput,
} from 'flowbite-react';
import { useState, useEffect } from 'react';
import { HiOutlineExclamationCircle } from '@react-icons/all-files/hi/HiOutlineExclamationCircle';

export default function Instituto() {
  const [data, setData] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);
  const [showCreateForm, setShowCreateForm] = useState(false);
  const [InfoCadInstituto, setInfoCadInstituto] = useState({
    nome: '',
    acronimo: '',
  });
  const [infoSearch, setInfoSearch] = useState({
    termo: '',
    campo: 'Todos',
  });

  const [objToDelete, setObjToDelete] = useState({});

  function handleDelete(instituto) {
    setObjToDelete(instituto), setShowDeleteConfirmation(true);
  }

  function handleChangeInfoCadInstituto(data) {
    const value = data.target.value;
    setInfoCadInstituto({
      ...InfoCadInstituto,
      [data.target.name]: value,
    });
  }

  function handleChangePesquisa(data) {
    const value = data.target.value;
    setInfoSearch({
      ...infoSearch,
      [data.target.name]: value,
    });
  }

  function handlePerquisarInstituto() {
    const termo = infoSearch.termo;
    const campo = infoSearch.campo;
    let url = '';

    switch (campo) {
      case 'Todos':
        url = 'http://localhost:8080/instituto/pesquisar/';
        break;
      case 'Nome':
        url = 'http://localhost:8080/instituto/nome/';
        break;
      case 'Acrônimo':
        url = 'http://localhost:8080/instituto/acronimo/';
        break;
    }

    console.log(campo);

    fetch(`${url}` + termo)
      .then(console.log('pesquisa realizada com o campo' + campo))
      .then((res) => res.json())
      .then((data) => {
        setData(data);
      })
      .catch((err) => console.log(err));

    console.log(data);
    console.log(`${url}` + termo);
  }

  function handleCreateInstituto() {
    fetch('http://localhost:8080/instituto/', {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        nome: InfoCadInstituto.nome,
        acronimo: InfoCadInstituto.acronimo,
      }),
    })
      .then((res) => {
        console.log(res);
        setShowCreateForm(!showCreateForm);
        setInfoCadInstituto({
          nome: '',
          acronimo: '',
        });
      })
      .catch((err) => {
        console.log(err);
      });
  }

  function deleteInstituto(id) {
    fetch(`http://localhost:8080/instituto/${id}`)
      .then(console.log('deleted'))
      .then(setShowDeleteConfirmation(!showDeleteConfirmation))
      .catch((err) => console.log(err));
  }

  useEffect(() => {
    setLoading(true);
    fetch('http://localhost:8080/instituto/')
      .then((res) => res.json())
      .then((data) => {
        setData(data);
        setLoading(false);
      })
      .catch((err) => console.log(err));
  }, [showDeleteConfirmation, showCreateForm]);

  if (isLoading) return <p>Loading...</p>;
  if (!data) return <p>No data</p>;

  return (
    <div className="mx-2">
      <div className="flex justify-between items-center">
        <h1 className="text-4xl font-bold px-4 py-6">Instituto</h1>

        <div className='flex justify-between items-center"'>
          <div className="block mr-4">
            <TextInput
              className="w-96"
              id="termo"
              name="termo"
              placeholder="Termo de pesquisa"
              value={infoSearch.termo}
              onChange={handleChangePesquisa}
            />
          </div>

          <div className="mr-4" id="select">
            <Select
              id="camp"
              name="campo"
              required={true}
              onChange={handleChangePesquisa}
            >
              <option>Todos</option>
              <option>Nome</option>
              <option>Acrônimo</option>
            </Select>
          </div>
          <div className="flex justify-between items-center">
            <button
              type="button"
              onClick={() => handlePerquisarInstituto()}
              className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            >
              Pesquisar
            </button>
          </div>
        </div>

        <button
          type="button"
          onClick={() => setShowCreateForm(true)}
          className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
        >
          Incluir
        </button>
      </div>

      <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" className="px-6 py-3">
                Nome Instituto
              </th>
              <th scope="col" className="px-6 py-3">
                Acrônimo
              </th>
              <th cope="col" className="px-6 py-3">
                Ações
              </th>
            </tr>
          </thead>
          <tbody>
            {data.map((Instituto) => (
              <tr
                key={Instituto.id}
                className="bg-white border-b dark:bg-gray-900 dark:border-gray-700"
              >
                <th
                  scope="row"
                  className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                >
                  {Instituto.nome}
                </th>
                <td className="px-6 py-4">{Instituto.acronimo}</td>
                <td className="px-6 py-4 space-x-3">
                  {/* <a
                    href="#"
                    className="font-medium text-blue-600 dark:text-blue-500 hover:underline"
                  >
                    Editar
                  </a> */}
                  <a
                    onClick={() => handleDelete(Instituto)}
                    className="font-medium text-red-600 dark:text-red-500 hover:underline cursor-pointer"
                  >
                    Excluir
                  </a>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* MODAL DELETE CONFIRMATION */}
      <>
        <Modal
          show={showDeleteConfirmation}
          size="md"
          popup={true}
          onClose={() => setShowDeleteConfirmation(!showDeleteConfirmation)}
          objToDelete={objToDelete}
        >
          <Modal.Header />
          <Modal.Body>
            <div className="text-center">
              <HiOutlineExclamationCircle className="mx-auto mb-4 h-14 w-14 text-gray-400 dark:text-gray-200" />
              <h3 className="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">
                Você tem certeza que deseja deletar o instituto{' '}
                {objToDelete.acronimo}?
              </h3>
              <div className="flex justify-center gap-4">
                <Button
                  color="failure"
                  onClick={() => deleteInstituto(objToDelete.id)}
                >
                  Sim, tenho ceteza
                </Button>
                <Button
                  color="gray"
                  onClick={() =>
                    setShowDeleteConfirmation(!showDeleteConfirmation)
                  }
                >
                  Não, cancelar
                </Button>
              </div>
            </div>
          </Modal.Body>
        </Modal>
      </>

      {/* MODAL INSERIR */}

      <>
        <Modal
          show={showCreateForm}
          size="md"
          popup={true}
          onClose={() => setShowCreateForm(!showCreateForm)}
        >
          <Modal.Header />
          <Modal.Body>
            <div className="space-y-6 px-6 pb-4 sm:pb-6 lg:px-8 xl:pb-8">
              <h3 className="text-xl font-medium text-gray-900 dark:text-white">
                Cadastrar Instituto
              </h3>
              <div>
                <div className="mb-2 block">
                  <Label htmlFor="Nome" value="Nome do Instituto" />
                </div>
                <TextInput
                  id="nome"
                  name="nome"
                  value={InfoCadInstituto.nome}
                  onChange={handleChangeInfoCadInstituto}
                  placeholder="Universidade Federal do Rio de Janeiro"
                  required={true}
                />
              </div>
              <div>
                <div className="mb-2 block">
                  <Label htmlFor="acronimo" value="Acrônimo" />
                </div>
                <TextInput
                  id="acronimo"
                  name="acronimo"
                  value={InfoCadInstituto.acronimo}
                  onChange={handleChangeInfoCadInstituto}
                  placeholder="UFRJ"
                  required={true}
                />
              </div>
              <div className="flex justify-between"></div>
              <div className="w-full">
                <Button onClick={() => handleCreateInstituto()}>
                  Cadastrar
                </Button>
              </div>
            </div>
          </Modal.Body>
        </Modal>
      </>
    </div>
  );
}
