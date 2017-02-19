class TransactionsController < ApplicationController
  before_action :set_transactions, only: [:show, :update, :destroy]

  def index
    @transactions = Transaction.all

    if params[:filter]
      case params[:filter]

      when "name"
        render json: get_by_name
      when "company_name"
        render json: get_by_organization_name
      when "urgency"
        render json: get_by_urgency
      end
    else
      render json: @transactions
    end
  end

  def create
    @transaction = Transaction.new
    @transaction.item_id = params[:item_id]
    @transaction.donation_id = params[:donation_id]
    @transaction.beneficiary_id = params[:beneficiary_id]
    @transaction.benefactor_id = params[:benefactor_id]
    @transaction.status = params[:status]

    if @transaction.save
      render json: @transaction, status: :created, location: @transaction
    else
      render json: @transaction.errors, status: :unprocessable_entity
    end
  end

  def show
    render json: @transactions
  end

  def update
    @transaction = Transaction.find(params[:id])

    if @transaction.update(transaction_params)
      head :no_content
    else
      render json: @transaction.errors, status: :unprocessable_entity
    end
  end

  def destroy
    @transaction.destroy

    head :no_content
  end



  private

    def set_transactions
      @transactions = Transactions.find(params[:id])
    end

    def transaction_params
      params.require(:transaction).permit!
    end

    def get_by_name
      Transactions.order(:transaction_name)
    end

    def get_by_organization_name
      Transactions.order(:organization_name)
    end

    def get_by_urgency
      Transactions.order(:urgency)
    end

end
